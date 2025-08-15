package project.jlog.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePwdDTO {
    @NotEmpty(message="비밀번호는 필수 항목입니다.")
    private String pwd1;
    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String pwd2;
}
