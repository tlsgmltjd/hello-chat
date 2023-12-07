package com.example.hellochat.domain.chat.service;

import com.example.hellochat.domain.chat.dto.ParticipateInfo;
import com.example.hellochat.domain.chat.dto.RoomResponse;
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

import static com.example.hellochat.global.exception.ErrorCode.DUPLICATED_USERNAME;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public List<RoomResponse> findRoom(UserEntity user) {
        return roomRepository.findByFromUserOrToUser(user, user).stream()
                .map(room ->
                        RoomResponse.builder()
                                .id(room.getId())
                                .toUser(ParticipateInfo.builder()
                                        .id(room.getToUser().getUsersId())
                                        .username(room.getToUser().getName())
                                        .build())
                                .fromUser(ParticipateInfo.builder()
                                        .id(room.getFromUser().getUsersId())
                                        .username(room.getFromUser().getName())
                                        .build())
                                .build())
                .toList();
    }

    public void createRoom(Long toUserId, Long fromUserId) {
        if (toUserId.equals(fromUserId)) throw new CustomException(DUPLICATED_USERNAME);

        UserEntity toUser = userRepository.findById(toUserId)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_MATCH_INFORMATION));
        UserEntity fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_MATCH_INFORMATION));

        roomRepository.save(Room.createRoom(toUser, fromUser));
    }

    public Chat createChat(Long roomId, String sender, String message) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    public List<Chat> findAllChatByRoomId(Long roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }


}
