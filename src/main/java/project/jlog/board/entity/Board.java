package project.jlog.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class Board {
    @Id //pk -> 인조식별자 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) //인조식별자 자동 증가로 중복 제거
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @CreationTimestamp //시간 자동 저장
    @Column(name = "create_date")
    private LocalDateTime date;
}
