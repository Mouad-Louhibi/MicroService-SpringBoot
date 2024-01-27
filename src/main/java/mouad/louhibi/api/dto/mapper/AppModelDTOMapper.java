package mouad.louhibi.api.dto.mapper;

import mouad.louhibi.api.controller.request.AppRequest;
import mouad.louhibi.api.dto.AppModelDTO;
import mouad.louhibi.api.dto.StudentModelDTO;
import mouad.louhibi.api.dto.SubjectModelDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppModelDTOMapper {

    public AppModelDTO mapToDTO(AppRequest request) {
        return new AppModelDTO()
                .setStudent(
                        new StudentModelDTO()
                                .setFname(request.getStudent().getFname())
                                .setLname(request.getStudent().getLname())
                                .setDob(request.getStudent().getDob())
                )
                .setSubject(
                        new SubjectModelDTO()
                                .setName(request.getSubject().getName())
                                .setSection(request.getSubject().getSection())
                                .setTeacher(request.getSubject().getTeacher())
                );
    }

    public AppModelDTO mapToDTO(Object obj) {

        if (obj instanceof Map) {
            Map<String, Object> dataMap = (Map<String, Object>) obj;

            return new AppModelDTO().setStudent(
                            new StudentModelDTO()
                                    .setFname((String) dataMap.get("student_fname"))
                                    .setLname((String) dataMap.get("student_lname"))
                                    .setDob((String) dataMap.get("student_dob"))
                    )
                    .setSubject(
                            new SubjectModelDTO()
                                    .setName((String) dataMap.get("subject_name"))
                                    .setSection((String) dataMap.get("subject_section"))
                                    .setTeacher((String) dataMap.get("subject_teacher"))
                    );

        }

        return null;
    }

    public List<AppModelDTO> mapToDTOs(Object obj) {
        return new ArrayList<>();
    }

}

