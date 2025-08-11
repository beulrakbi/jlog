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
import project.jlog.user.entity.User;
import project.jlog.user.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    /* 등록 컨트롤러 시작*/
    @GetMapping("/posting")
    public String boardCreate(BoardRequestDTO boardRequestDTO){
        return "posting";
    }
    /* @Valid : notblank나 size 어노테이션을 적용시키기 위해 제약조건을 검사하는 것 */
    @PostMapping("/posting")
    public String boardCreate(@Valid BoardRequestDTO boardRequestDTO, BindingResult bindingResult, Principal principal){
        User user = userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            return "posting";
        }
        this.boardService.boardCreate(boardRequestDTO.getSubject(), boardRequestDTO.getContent(), user);
        return "redirect:/";
    }
    /* 등록 컨트롤러 종료 */

    /* 조회 컨트롤러 시작 */
    // -> 리스트 조회 페이지는 메인페이지이기 때문에 메인 컨트롤러에 작성
    /* 조회 컨트롤러 종료 */

    /* 상세페이지 시작 */
    @GetMapping("/post/{boardId}")
    public String boardDetail(Model model, @PathVariable Long boardId) {
        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "post";
    }
    /* 상세페이지 종료 */

    /* 수정하기 시작*/

    /* 수정하기 끝*/
}
