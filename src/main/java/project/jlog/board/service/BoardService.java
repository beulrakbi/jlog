package project.jlog.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.jlog.exception.DataNotFoundException;
import project.jlog.board.entity.Board;
import project.jlog.board.repository.BoardRepository;
import project.jlog.user.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 등록
    public Board boardCreate(String subject, String content, User user){ //빌더 사용시 Board 객체를 반환해야 함
        Board board = Board.builder()
                .subject(subject)
                .content(content)
                .userId(user)
                .build();
        return boardRepository.save(board);
    }

    //게시글 조회
    public List<Board> getList(){
        return this.boardRepository.findAll();
    }

    //게시글 상세페이지
    public Board getBoard(Long boardId) {
        Optional<Board> board = this.boardRepository.findById(boardId);
        if(board.isPresent()){
            return board.get();
        } else{
            throw new DataNotFoundException("question not found");
        }
    }

    //수정

}
