package com.example.hellochat.domain.chat.repository;


import com.example.hellochat.domain.chat.entity.Room;
import com.example.hellochat.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByFromUserOrToUser(UserEntity fromUser, UserEntity toUser);
}
