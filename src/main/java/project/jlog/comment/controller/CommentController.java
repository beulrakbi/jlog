package project.jlog.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.jlog.board.entity.Board;
import project.jlog.board.service.BoardService;
import project.jlog.comment.dto.CommentDTO;
import project.jlog.comment.entity.Comment;
import project.jlog.comment.repository.CommentRepository;
import project.jlog.comment.service.CommentService;
import project.jlog.user.entity.User;
import project.jlog.user.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final BoardService boardService;

//    @GetMapping("/create")
//    public String commentCreate(CommentDTO commentDTO, Model model){
//        model.addAttribute("commentDTO", new CommentDTO());
//        return "/post";
//    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{boardId}")
    public String commentCreate(@Valid CommentDTO commentDTO, @PathVariable("boardId") Long boardId, BindingResult bindingResult, Principal principal){
        User user = userService.getUser(principal.getName());
        Board board = this.boardService.getBoard(boardId);

        if(bindingResult.hasErrors()){
            return "post";
        }
        this.commentService.createComment(commentDTO.getCommentContent(), user, board);
        return "redirect:/board/post/" + boardId;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{commentId}")
    public String commentDelete(Principal principal, @PathVariable("commentId") Long commentId){
        Comment comment = this.commentService.getComment(commentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."
                ));
        if (!comment.getUser().getUserId().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.commentService.delete(comment);
        return "redirect:/board/post/" + comment.getBoard().getBoardId();
    }

}
