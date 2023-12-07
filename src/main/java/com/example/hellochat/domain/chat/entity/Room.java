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

    @OneToMany
    private List<UserEntity> participates;

    public static Room createRoom(List<UserEntity> participates) {
        return Room.builder()
                .participates(participates)
                .build();
    }

}