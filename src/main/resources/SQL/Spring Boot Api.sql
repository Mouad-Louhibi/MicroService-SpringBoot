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

DELIMITER //

CREATE PROCEDURE get_students()
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
    FROM
        student s
    LEFT JOIN
        subject su ON s.id = su.student_id;
END //

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

DELIMITER //

CREATE PROCEDURE update_student(
    IN p_student_id INT,
    IN p_fname VARCHAR(255),
    IN p_lname VARCHAR(255),
    IN p_dob VARCHAR(255),
    IN p_subject_name VARCHAR(255),
    IN p_subject_section VARCHAR(255),
    IN p_subject_teacher VARCHAR(255)
)
BEGIN
    -- Check if the student exists
    IF EXISTS (SELECT 1 FROM student WHERE id = p_student_id) THEN
        -- Update the student record
        UPDATE student
        SET
            fname = p_fname,
            lname = p_lname,
            dob = p_dob
        WHERE id = p_student_id;

        -- Check if the subject exists
        IF EXISTS (SELECT 1 FROM subject WHERE student_id = p_student_id) THEN
            -- Update the subject record
            UPDATE subject
            SET
                name = p_subject_name,
                section = p_subject_section,
                teacher = p_subject_teacher
            WHERE student_id = p_student_id;
        ELSE
            -- If subject does not exist, you may choose to insert a new subject record here
            INSERT INTO subject (name, section, teacher, student_id) VALUES (p_subject_name, p_subject_section, p_subject_teacher, p_student_id);
        END IF;

        SELECT 'Student and subject records updated successfully' AS result;
    ELSE
        SELECT 'Student not found' AS result;
    END IF;
END //

DELIMITER ;


SELECT * FROM student;
SELECT * FROM subject;

DELETE FROM student WHERE id > 0;
DELETE FROM subject WHERE id > 0;
