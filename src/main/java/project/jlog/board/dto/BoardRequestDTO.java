package project.jlog.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import project.jlog.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardRequestDTO {
    private Long boardId;

    @NotBlank(message = "제목은 필수 항목입니다.")
    @Size(min = 3, max = 20)
    private String subject;
    @NotBlank(message = "내용은 필수 항목입니다.")
    private String content;
    private LocalDateTime date;
}
