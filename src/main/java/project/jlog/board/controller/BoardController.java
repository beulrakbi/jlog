package project.jlog.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.jlog.board.dto.BoardCreateDTO;
import project.jlog.board.entity.Board;
import project.jlog.board.service.BoardService;

//@Controller
@RestController // 테스트를 위해 RestController를 사용
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /* @Valid : notblank나 size 어노테이션을 적용시키기 위해 제약조건을 검사하는 것
    * @RequestBody : HTTP 요청의 body에 담긴 JSON 데이터를 자바 객체로 변환해주는 어노테이션  */
    @PostMapping("/create")
    public Board boardCreate(@Valid @RequestBody BoardCreateDTO boardCreateDTO){
        return boardService.create(boardCreateDTO.getSubject(), boardCreateDTO.getContent());
    }
}
