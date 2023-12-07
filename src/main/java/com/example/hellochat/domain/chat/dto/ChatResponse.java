package com.example.hellochat.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponse {

    private Long roomId;
    private String sender;
    private Boolean isMe;
    private String message;
    private LocalDateTime sendDate;

}
