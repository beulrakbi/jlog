package project.jlog.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.jlog.board.entity.Board;
import project.jlog.board.repository.BoardRepository;

import javax.swing.border.Border;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 등록 메서드
    public Board create(String subject, String content){ //빌더 사용시 Board 객체를 반환해야 함
        Board board = Board.builder()
                .subject(subject)
                .content(content)
                .build();
        return boardRepository.save(board);
    }

    // 게시글 삭제 메서드
        
}
