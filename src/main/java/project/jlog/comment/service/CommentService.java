package project.jlog.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.jlog.board.entity.Board;
import project.jlog.comment.entity.Comment;
import project.jlog.comment.repository.CommentRepository;
import project.jlog.exception.DataNotFoundException;
import project.jlog.user.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment createComment(String commentContent, User user, Board board){
        Comment comment = Comment.builder()
                .commentContent(commentContent)
                .user(user)
                .board(board)
                .build();
        return this.commentRepository.save(comment);
    }

    public List<Comment> getCommentsByBoard(Long boardId) {
        return commentRepository.findAllByBoard_BoardIdOrderByCreateDateAsc(boardId);
    }

    public void delete(Comment comment){
        this.commentRepository.delete(comment);
    }

    public Optional<Comment> getComment(Long commentId){
        return commentRepository.findById(commentId);
    }
}
