package com.example.hellochat.domain.comment.entity;

import com.example.hellochat.domain.board.entity.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @CreatedDate
    private LocalDateTime date;

    @ElementCollection
    private List<Long> likes;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board boardId;

    public Comment(String content, Board boardId) {
        this.content = content;
        this.boardId = boardId;
    }
}
