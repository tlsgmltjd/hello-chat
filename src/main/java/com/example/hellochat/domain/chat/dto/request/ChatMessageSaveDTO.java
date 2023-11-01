package com.example.hellochat.domain.chat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageSaveDTO {
    private String roomId;
    private String writer;
    private String message;

}