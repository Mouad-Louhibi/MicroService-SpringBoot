package mouad.louhibi.api.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AppRequest {
    private StudentRequest student;
    private SubjectRequest subject;
}
