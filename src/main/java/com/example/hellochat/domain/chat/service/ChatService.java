package com.example.hellochat.domain.chat.service;

import com.example.hellochat.domain.chat.entity.Chat;
import com.example.hellochat.domain.chat.entity.Room;
import com.example.hellochat.domain.chat.repository.ChatRepository;
import com.example.hellochat.domain.chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;

    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    public void createRoom(String name) {
        roomRepository.save(Room.createRoom(name));
    }

    public Chat createChat(Long roomId, String sender, String message) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    public List<Chat> findAllChatByRoomId(Long roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }


}
