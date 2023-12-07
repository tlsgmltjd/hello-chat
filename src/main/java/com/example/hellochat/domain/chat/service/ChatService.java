package com.example.hellochat.domain.chat.service;

import com.example.hellochat.domain.chat.entity.Chat;
import com.example.hellochat.domain.chat.entity.Room;
import com.example.hellochat.domain.chat.repository.ChatRepository;
import com.example.hellochat.domain.chat.repository.RoomRepository;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.domain.user.repository.UserRepository;
import com.example.hellochat.global.exception.CustomException;
import com.example.hellochat.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public List<Room> findRoom() {
        return roomRepository.findAll();
    }

    public void createRoom(List<Long> participateIds) {

        List<UserEntity> paticipates = participateIds.stream().map(id -> userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_MATCH_INFORMATION))).toList();

        roomRepository.save(Room.createRoom(paticipates));
    }

    public Chat createChat(Long roomId, String sender, String message) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    public List<Chat> findAllChatByRoomId(Long roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }


}
