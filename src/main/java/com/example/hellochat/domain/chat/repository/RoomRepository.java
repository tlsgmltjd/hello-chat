package com.example.hellochat.domain.chat.repository;


import com.example.hellochat.domain.chat.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
