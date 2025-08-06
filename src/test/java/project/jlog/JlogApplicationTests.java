package project.jlog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.jlog.board.dto.BoardRequestDTO;
import project.jlog.board.entity.Board;
import project.jlog.board.repository.BoardRepository;
import project.jlog.board.service.BoardService;

import java.time.LocalDateTime;

@SpringBootTest
class JlogApplicationTests {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    @Test
    void 게시글작성테스트()  {
        BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
        boardRequestDTO.setSubject("글쓰기 테스트");
        boardRequestDTO.setContent("글쓰기 테스트중입니다.");
        boardRequestDTO.setDate(LocalDateTime.now());

        Board b = Board.builder()
                .subject(boardRequestDTO.getSubject())
                .content(boardRequestDTO.getContent())
                .date(LocalDateTime.now())
                .build();
        this.boardRepository.save(b);
    }

}
