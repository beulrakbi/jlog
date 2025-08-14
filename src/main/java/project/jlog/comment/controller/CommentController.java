package project.jlog.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
