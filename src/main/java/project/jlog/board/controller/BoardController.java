package project.jlog.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.jlog.board.dto.BoardRequestDTO;
import project.jlog.board.entity.Board;
import project.jlog.board.service.BoardService;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/posting")
    public String boardCreate(BoardRequestDTO boardRequestDTO){
        return "posting";
    }
    /* @Valid : notblank나 size 어노테이션을 적용시키기 위해 제약조건을 검사하는 것 */
    @PostMapping("/posting")
    public String boardCreate(@Valid BoardRequestDTO boardRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "posting";
        }
        this.boardService.create(boardRequestDTO.getSubject(), boardRequestDTO.getContent());
        return "redirect:/";
    }
}
