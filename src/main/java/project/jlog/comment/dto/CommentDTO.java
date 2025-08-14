package project.jlog.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    @NotBlank(message = "내용을 작성해주세요.")
    private String commentContent;
}
