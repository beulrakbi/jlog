package project.jlog.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.jlog.board.entity.Board;
import project.jlog.user.entity.User;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByBoardIdDesc(); //메인페이지에 전체 게시글 내림차순

    @Query(value = "SELECT * FROM board ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Board> findRandomBoards();

    Page<Board> findByUser_UserIdOrderByDateDesc(String userId, Pageable pageable); //마이홈에서 사용

}
