package com.example.hellochat.domain.board.entity;

import com.example.hellochat.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime date;

    @ElementCollection
    private List<Long> likes;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity authorId;
}
