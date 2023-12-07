package com.example.hellochat.domain.chat.entity;

import com.example.hellochat.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private UserEntity toUser;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private UserEntity fromUser;

    public static Room createRoom(UserEntity toUser, UserEntity fromUser) {
        return Room.builder()
                .toUser(toUser)
                .fromUser(fromUser)
                .build();
    }

}