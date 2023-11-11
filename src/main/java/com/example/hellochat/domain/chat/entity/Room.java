package com.example.hellochat.domain.chat.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String name;

    public static Room createRoom(String name) {
        return Room.builder()
                .name(name)
                .build();
    }

}