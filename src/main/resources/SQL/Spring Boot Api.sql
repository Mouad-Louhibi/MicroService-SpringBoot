-- Create the student table
ALTER TABLE student
MODIFY COLUMN dob VARCHAR(255);

-- Create the subject table
DROP TABLE IF EXISTS subject;
CREATE TABLE subject (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    section VARCHAR(255) NOT NULL,
    teacher VARCHAR(255) NOT NULL,
    student_id INT,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
);


DROP PROCEDURE IF EXISTS create_student;

DELIMITER //

CREATE PROCEDURE create_student (
    IN student_fname VARCHAR(255),
    IN student_lname VARCHAR(255),
    IN student_dob VARCHAR(255),
    IN subject_name VARCHAR(255),
    IN subject_section VARCHAR(255),
    IN subject_teacher VARCHAR(255)
)
BEGIN
    DECLARE student_id INT;

    -- Insert into the student table
    INSERT INTO student (fname, lname, dob)
    VALUES (student_fname, student_lname, student_dob);

    -- Get the last inserted student ID
    SET student_id = LAST_INSERT_ID();

    -- Insert into the subject table
    INSERT INTO subject (name, section, teacher, student_id)
    VALUES (subject_name, subject_section, subject_teacher, student_id);

    -- You can return additional information if needed
    -- For example, you can return the newly created student ID
    SELECT student_id AS created_student_id;
END //

DELIMITER //

DROP PROCEDURE IF EXISTS get_student;
CREATE PROCEDURE get_student(IN student_id INT)
BEGIN
    SELECT
        s.id AS student_id,
        s.fname AS student_fname,
        s.lname AS student_lname,
        s.dob AS student_dob,
        su.id AS subject_id,
        su.name AS subject_name,
        su.section AS subject_section,
        su.teacher AS subject_teacher
    FROM student s
    LEFT JOIN subject su ON s.id = su.student_id
    WHERE s.id = student_id;
END //

DELIMITER ;


SELECT * FROM springboot.student;
SELECT * FROM springboot.subject;

DELIMITER //

DROP PROCEDURE IF EXISTS get_student;

DELIMITER //
CREATE PROCEDURE get_student (
	IN student_id INT
)
BEGIN
    SELECT *
    FROM student
    WHERE id = student_id
		AND dob IS NOT NULL
		AND dob != '0000-00-00';
END //

INSERT INTO student (fname, lname, dob)
VALUES
  ('John', 'Doe', '1990-01-15'),
  ('Jane', 'Smith', '1992-05-20'),
  ('Mike', 'Johnson', '1988-11-10'),
  ('Emily', 'Williams', '1995-03-25'),
  ('David', 'Brown', '1998-09-08'),
  ('Sophia', 'Miller', '1993-07-12'),
  ('Daniel', 'Taylor', '1991-04-18'),
  ('Olivia', 'Clark', '1989-06-30'),
  ('Matthew', 'Moore', '1997-02-05'),
  ('Emma', 'Jones', '1994-08-22');
  
DELETE FROM student
WHERE id = 1;

SELECT * FROM student;
SELECT * FROM subject;

DELIMITER //
CREATE PROCEDURE get_students ()
BEGIN
    SELECT * FROM student;
END //

DELIMITER //

CREATE PROCEDURE update_student (
    IN student_id INT,  -- New parameter for student ID
    IN student_fname VARCHAR(255),
    IN student_lname VARCHAR(255),
    IN student_dob VARCHAR(255), 
    IN subject_name VARCHAR(255),
    IN subject_section VARCHAR(255),
    IN subject_teacher VARCHAR(255)
)
BEGIN
    DECLARE subject_id_var INT;

    -- Get subject ID based on subject name
    SELECT subject_id_column INTO subject_id_var
    FROM subject
    WHERE name = subject_name;

    -- Update student information
    UPDATE student
    SET
        fname = student_fname,
        lname = student_lname,
        dob = student_dob
    WHERE
        student_id_column = student_id; -- Use the provided student ID parameter

    -- Update subject information
    UPDATE subject
    SET
        section = subject_section,
        teacher = subject_teacher
    WHERE
        subject_id_column = subject_id_var; -- Use the retrieved subject ID
END //

DELIMITER //

CREATE PROCEDURE delete_student(IN student_id INT)
BEGIN
    -- Check if the student exists
    IF EXISTS (SELECT 1 FROM student WHERE id = student_id) THEN
        -- Delete the student record
        DELETE FROM student WHERE id = student_id;
        SELECT 'Student deleted successfully' AS result;
    ELSE
        SELECT 'Student not found' AS result;
    END IF;
END //

DELIMITER ;

DELETE FROM student WHERE id > 0;
DELETE FROM subject WHERE id > 0;
