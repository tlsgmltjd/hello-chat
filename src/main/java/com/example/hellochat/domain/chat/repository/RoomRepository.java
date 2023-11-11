package com.example.hellochat.domain.chat.repository;


import com.example.hellochat.domain.chat.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
