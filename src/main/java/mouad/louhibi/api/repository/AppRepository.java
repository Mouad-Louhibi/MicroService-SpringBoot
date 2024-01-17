package mouad.louhibi.api.repository;

import jakarta.annotation.PostConstruct;
import mouad.louhibi.api.model.AppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

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
                .addValue("student_lname", model.getStudent().getFname())
                .addValue("student_dob", model.getStudent().getFname())
                .addValue("subject_name", model.getSubject().getName())
                .addValue("subject_section", model.getSubject().getSection())
                .addValue("subject_teacher", model.getSubject().getTeacher());

        return simpleJdbcCall.execute(in);
    }
}
