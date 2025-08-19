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

}
