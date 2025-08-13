package project.jlog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.jlog.board.entity.Board;
import project.jlog.board.service.BoardService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    public final BoardService boardService;
    @GetMapping("/")
    public String list(Model model){
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
        return "index";
    }
}

