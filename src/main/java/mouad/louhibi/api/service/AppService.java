package mouad.louhibi.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mouad.louhibi.api.dto.AppModelDTO;
import mouad.louhibi.api.model.mapper.AppModelMapper;
import mouad.louhibi.api.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AppService {

    @Autowired
    private AppRepository repository;

    public Integer createStudentRecord(AppModelDTO dto){
        Map<String, Object> out = repository.createStudentRecord(new AppModelMapper().mapToModel(dto));
        ObjectMapper mapper = new ObjectMapper();
        return (Integer) (mapper.convertValue(((ArrayList<?>) out.get("#result-set-1")).get(0), Map.class)).get("created_student_id");
    }
    public Integer updateStudent(AppModelDTO dto, Integer id) {
        Map<String, Object> out = repository.updateStudentRecord(new AppModelMapper().mapToModel(dto), id);
        ObjectMapper mapper = new ObjectMapper();
        return (Integer) (mapper.convertValue(((ArrayList<?>) out.get("#result-set-1")).get(0), Map.class)).get("updated_student_id");
    }

    public Object getStudent(Integer id) {
        Map<String, Object> out = repository.getStudent(id);

        if (out != null && out.containsKey("#result-set-1")) {
            ArrayList<?> resultSet = (ArrayList<?>) out.get("#result-set-1");

            if (!resultSet.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.convertValue(resultSet.get(0), Map.class);
            }
        }

        return null;
    }

    public Integer deleteStudent(Integer id) {
        Map<String, Object> out = repository.deleteStudent(id);
        ObjectMapper mapper = new ObjectMapper();
        return (Integer) mapper.convertValue(((ArrayList<?>) out.get("#result-set-1")).get(0), Map.class).get("id");
    }

    public Object getStudents() {
        Map<String, Object> out = repository.getStudents();

        // Assuming you have an output parameter named 'out_result_set' in your stored procedure
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) out.get("out_result_set");

        // Process the data in the result set
        for (Map<String, Object> row : resultSet) {
            // Access individual columns using column names
            Object studentId = row.get("student_id");
            Object studentName = row.get("student_name");
        }
        return (ArrayList<?>) out.get("#result-set-1");
    }
}
