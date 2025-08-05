package project.jlog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.jlog.board.dto.BoardCreateDTO;
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
        BoardCreateDTO boardCreateDTO = new BoardCreateDTO();
        boardCreateDTO.setSubject("글쓰기 테스트");
        boardCreateDTO.setContent("글쓰기 테스트중입니다.");
        boardCreateDTO.setDate(LocalDateTime.now());

        Board b = Board.builder()
                .subject(boardCreateDTO.getSubject())
                .content(boardCreateDTO.getContent())
                .date(LocalDateTime.now())
                .build();
        this.boardRepository.save(b);
    }

}
