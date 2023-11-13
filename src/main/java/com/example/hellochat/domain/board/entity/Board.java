package com.example.hellochat.domain.board.entity;

import com.example.hellochat.domain.comment.entity.Comment;
import com.example.hellochat.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) // Jpa Event가 발샐할 때마다 특정 로직을 수행시킬 수 있는 어노테이션
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime date;

    @ElementCollection
    private List<Long> likes;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity authorId;

    @OneToMany(mappedBy = "boardId")
    private List<Comment> comment;

    public void addLike(Long userId) {
        likes.add(userId);
    }

    public void removeLike(Long userId) {
        likes.remove(userId);
    }

}
