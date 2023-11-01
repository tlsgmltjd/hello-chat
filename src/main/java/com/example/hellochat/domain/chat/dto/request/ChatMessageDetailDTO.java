package com.example.hellochat.domain.chat.dto.request;

import com.example.hellochat.domain.chat.entity.ChatMessage;
import com.example.hellochat.domain.chat.entity.ChatRoom;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDetailDTO {

    private Long chatId;
    private Long chatRoomId;

    private String roomId;
    private String writer;
    private String message;

    public static ChatMessageDetailDTO toChatMessageDetailDTO(ChatMessage chatMessageEntity){
        ChatMessageDetailDTO chatMessageDetailDTO = new ChatMessageDetailDTO();

        chatMessageDetailDTO.setChatId(chatMessageEntity.getId());

        chatMessageDetailDTO.setChatRoomId(chatMessageEntity.getChatRoom().getId());
        chatMessageDetailDTO.setRoomId(chatMessageEntity.getChatRoom().getRoomId());

        chatMessageDetailDTO.setWriter(chatMessageEntity.getWriter());
        chatMessageDetailDTO.setMessage(chatMessageEntity.getMessage());

        return chatMessageDetailDTO;

    }

}