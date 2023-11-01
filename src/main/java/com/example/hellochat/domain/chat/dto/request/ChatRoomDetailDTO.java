package com.example.hellochat.domain.chat.dto.request;

import com.example.hellochat.domain.chat.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDetailDTO {
    private Long chatRoomId;
    private String chatMentor;
    private String roomId;
    private String name;

    public static ChatRoomDetailDTO toChatRoomDetailDTO(ChatRoom chatRoomEntity){
        ChatRoomDetailDTO chatRoomDetailDTO = new ChatRoomDetailDTO();

        chatRoomDetailDTO.setChatRoomId(chatRoomEntity.getId());
        chatRoomDetailDTO.setChatMentor(chatRoomEntity.getChatMentor());
        chatRoomDetailDTO.setRoomId(chatRoomEntity.getRoomId());
        chatRoomDetailDTO.setName(chatRoomEntity.getRoomName());

        return chatRoomDetailDTO;
    }

}