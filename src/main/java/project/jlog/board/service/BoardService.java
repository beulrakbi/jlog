package project.jlog.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.jlog.exception.DataNotFoundException;
import project.jlog.board.entity.Board;
import project.jlog.board.repository.BoardRepository;
import project.jlog.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 등록
    public Board boardCreate(String subject, String content, User userId){ //빌더 사용시 Board 객체를 반환해야 함
        Board board = Board.builder()
                .subject(subject)
                .content(content)
                .user(userId)
                .build();
        return boardRepository.save(board);
    }

    //게시글 조회
    public List<Board> getList(){
        return this.boardRepository.findRandomBoards();
    }

    //마이홈 게시글 조회
    public Page<Board> getMyPost(String userId, int page, int size){
        Pageable pageable = PageRequest.of(page, size); // 페이지 번호와 사이즈 설정
        return this.boardRepository.findByUser_UserIdOrderByDateDesc(userId, pageable);
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

    //삭제
    public void delete(Board board){
        this.boardRepository.delete(board);
    }

    //수정하기
    public void edit(Board board, String subject, String content) {
        Board updated = Board.builder()
                .boardId(board.getBoardId())
                .subject(subject)
                .content(content)
                .user(board.getUser())
                .modifyDate(LocalDateTime.now())  // 수정 시간 업데이트
                .build();
        boardRepository.save(updated);  // ID 있으면 JPA update
    }

}
