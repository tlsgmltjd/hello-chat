package com.example.hellochat.domain.user.entity;

import com.example.hellochat.domain.Board.entity.Board;
import com.example.hellochat.global.util.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long usersId;

    @Column(name = "name")
    private String name;

    @Column(name = "explain")
    private String explain;

    @Column(name = "birth")
    private Integer birth;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "authorId")
    private List<Board> posts;
}
