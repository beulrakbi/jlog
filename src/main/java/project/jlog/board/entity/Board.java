package project.jlog.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import project.jlog.user.entity.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class Board {
    @Id //pk -> 인조식별자 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) //인조식별자 자동 증가로 중복 제거
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @CreationTimestamp //시간 자동 저장
    @Column(name = "create_date", updatable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate; //수정 날짜 추가
}
