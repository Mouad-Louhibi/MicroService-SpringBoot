package mouad.louhibi.api.dto.mapper;

import mouad.louhibi.api.controller.request.AppRequest;
import mouad.louhibi.api.dto.AppModelDTO;
import mouad.louhibi.api.dto.StudentModelDTO;
import mouad.louhibi.api.dto.SubjectModelDTO;

import java.util.ArrayList;
import java.util.List;

public class AppModelDTOMapper {

    public AppModelDTO mapToDTO(AppRequest request){
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
                )
                ;
    }

    public AppModelDTO mapToDTO(Object obj) {
        return new AppModelDTO();
    }

    public List<AppModelDTO> mapToDTOs(Object obj) {
        return new ArrayList<>();
    }
}
