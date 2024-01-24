package mouad.louhibi.api.repository;

import jakarta.annotation.PostConstruct;
import mouad.louhibi.api.model.AppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AppRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @PostConstruct
    public void init(){
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        jdbcTemplate.setIgnoreWarnings(false);
    }
    public Map<String, Object> createStudentRecord(AppModel model){
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("create_student");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("student_fname", model.getStudent().getFname())
                .addValue("student_lname", model.getStudent().getLname())
                .addValue("student_dob", model.getStudent().getDob())
                .addValue("subject_name", model.getSubject().getName())
                .addValue("subject_section", model.getSubject().getSection())
                .addValue("subject_teacher", model.getSubject().getTeacher());

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> updateStudentRecord(AppModel model, Integer id) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("update_student");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("student_id", id)
                .addValue("student_fname", model.getStudent().getFname())
                .addValue("student_lname", model.getStudent().getLname())
                .addValue("student_dob", model.getStudent().getDob())
                .addValue("subject_name", model.getSubject().getName())
                .addValue("subject_section", model.getSubject().getSection())
                .addValue("subject_teacher", model.getSubject().getTeacher());

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> getStudent(Integer id) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("get_student");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("student_id", id);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> getStudents() {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("get_students");
        SqlParameterSource in = new MapSqlParameterSource();

        Map<String, Object> result = getStudents();

        // Assuming you have an output parameter named 'out_result_set' in your stored procedure
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("out_result_set");

        // Process the data in the result set
        for (Map<String, Object> row : resultSet) {
            // Access individual columns using column names
            Object studentId = row.get("student_id");
            Object studentName = row.get("student_name");
        }

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> deleteStudent(Integer id) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("delete_student");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("student_id", id);

        return simpleJdbcCall.execute(in);
    }
}
