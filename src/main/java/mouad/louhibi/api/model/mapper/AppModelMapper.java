package mouad.louhibi.api.model.mapper;

import mouad.louhibi.api.dto.AppModelDTO;
import mouad.louhibi.api.model.AppModel;
import mouad.louhibi.api.model.StudentModel;
import mouad.louhibi.api.model.SubjectModel;

public class AppModelMapper {

    public AppModel mapToModel(AppModelDTO dto){
        return new AppModel()
                .setStudent(
                        new StudentModel()
                                .setFname(dto.getStudent().getFname())
                                .setLname(dto.getStudent().getLname())
                                .setDob(dto.getStudent().getDob())
                )
                .setSubject(
                        new SubjectModel()
                                .setName(dto.getSubject().getName())
                                .setSection(dto.getSubject().getSection())
                                .setTeacher(dto.getSubject().getTeacher())
                )
                ;
    }
}
