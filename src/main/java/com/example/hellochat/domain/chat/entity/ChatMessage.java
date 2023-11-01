package com.example.hellochat.domain.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "chat_table")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom;

    private String writer;

    @Column
    private String message;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime sendDate;

    public static ChatMessage toChatEntity(ChatMessageSaveDTO chatMessageSaveDTO, ChatRoom chatRoom){
        ChatMessage chatMessage = new ChatMessage();

        chatMessage.setChatRoom(chatRoom);

        chatMessage.setWriter(chatMessageSaveDTO.getWriter());
        chatMessage.setMessage(chatMessageSaveDTO.getMessage());

        return chatMessage;

    }
}