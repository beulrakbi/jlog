package project.jlog.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.jlog.board.dto.BoardRequestDTO;
import project.jlog.board.entity.Board;
import project.jlog.board.service.BoardService;
import project.jlog.common.CommonUtil;
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
    private final CommonUtil commonUtil;

    //마이홈
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myhome")
    public String list(Model model, Principal principal){
        List<Board> boardList = this.boardService.getMyPost(principal.getName());

        model.addAttribute("boardList", boardList);
        return "myhome";
    }

    /* 등록 컨트롤러 시작*/
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/posting")
    public String boardCreate(BoardRequestDTO boardRequestDTO, Model model){
        model.addAttribute("boardRequestDTO", new BoardRequestDTO());
        return "posting";
    }
    /* @Valid : notblank나 size 어노테이션을 적용시키기 위해 제약조건을 검사하는 것 */

    @PreAuthorize("isAuthenticated()") // 이 어노테이션이 동작할 수 있게 스프링 시큐리티도 설정해야 함
    @PostMapping("/posting")
    public String boardCreate(@Valid BoardRequestDTO boardRequestDTO, BindingResult bindingResult, Principal principal){
        User user = userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            return "posting";
        }
        this.boardService.boardCreate(boardRequestDTO.getSubject(), boardRequestDTO.getContent(), user);
        return "redirect:/board/myhome";
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

    //삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{boardId}")
    public String boardDelete(Principal principal, @PathVariable("boardId") Long boardId){
        Board board = this.boardService.getBoard(boardId);
        if(!board.getUser().getUserId().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.boardService.delete(board);
        return "redirect:/";
    }

    //수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{boardId}")
    public String boardEdit(BoardRequestDTO boardRequestDTO, @PathVariable("boardId") Long boardId, Principal principal){
        Board board1 = this.boardService.getBoard(boardId);
        if(!board1.getUser().getUserId().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        boardRequestDTO.setSubject(board1.getSubject());
        boardRequestDTO.setContent(board1.getContent());
        return "posting";
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/edit/{boardId}")
    public String boardEdit(@Valid BoardRequestDTO boardRequestDTO, BindingResult bindingResult, Principal principal
    , @PathVariable("boardId") Long boardId){
        if(bindingResult.hasErrors()){
            return "posting";
        }
        Board board = this.boardService.getBoard(boardId);
        if(!board.getUser().getUserId().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        this.boardService.edit(board, boardRequestDTO.getSubject(), boardRequestDTO.getContent());
        return String.format("redirect:/board/post/%s", boardId);
    }
}
