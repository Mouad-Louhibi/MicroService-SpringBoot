package mouad.louhibi.api.controller.api;

import mouad.louhibi.api.controller.request.AppRequest;
import mouad.louhibi.api.dto.AppModelDTO;
import mouad.louhibi.api.dto.mapper.AppModelDTOMapper;
import mouad.louhibi.api.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private AppService service;

    @PostMapping(name = "CreateStudentRecord", value = "/student", consumes = "application/json")
    public ResponseEntity<Integer> createStudentRecordV1(@RequestBody AppRequest request) {
        Integer record = service.createStudentRecord(new AppModelDTOMapper().mapToDTO(request));
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    @GetMapping(name = "GetStudent", value = "/student"  , produces = "application/json")
    public ResponseEntity<AppModelDTO> getStudentV1(@RequestParam() Integer id) {
        Object result = service.getStudent(id);

        if (result != null) {
            AppModelDTO student = new AppModelDTOMapper().mapToDTO(result);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // or another appropriate response
        }
    }

    @GetMapping(name = "GetStudents", value = "/students", produces = "application/json")
    public ResponseEntity<List<AppModelDTO>> getStudentsV1() {
        List<AppModelDTO> students =  new AppModelDTOMapper().mapToDTOs(service.getStudents());
        System.out.println("Controller: " + service.getStudents());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping(name = "DeleteStudent", value = "/student", produces = "application/json")
    public ResponseEntity<Integer> deleteStudentV1(@RequestParam() Integer id) {
        Integer record = service.deleteStudent(id);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @PutMapping(name = "UpdateStudent", value = "/student", consumes = "application/json")
    public ResponseEntity<Integer> updateStudentV1(@RequestBody AppRequest request, @RequestParam Integer id) {
        Integer record = service.updateStudent(new AppModelDTOMapper().mapToDTO(request), id);
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }
}
