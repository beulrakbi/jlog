package project.jlog.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardCreateDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String subject;
    @NotBlank
    private String content;

    private LocalDateTime date;
}
