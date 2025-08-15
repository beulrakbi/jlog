package project.jlog.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    @NotBlank(message = "이메일은 필수 항목입니다.")
    private String email;
    @NotBlank(message = "인증코드는 필수 항목입니다.")
    private String code;
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;
}
