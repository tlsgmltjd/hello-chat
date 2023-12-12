package com.example.hellochat.domain.chat.controller;

import com.example.hellochat.domain.chat.dto.ChatMessage;
import com.example.hellochat.domain.chat.service.ChatService;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.domain.user.repository.UserRepository;
import com.example.hellochat.global.exception.CustomException;
import com.example.hellochat.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.example.hellochat.global.exception.ErrorCode.INVALID_TOKEN;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @MessageMapping("/{roomId}")
    @SendTo("/room/{roomId}")
    public ChatMessage test(@DestinationVariable Long roomId, ChatMessage message) {
        if (!jwtUtil.validateToken(message.getToken().substring(7))) {
            throw new CustomException(INVALID_TOKEN);
        }

        Claims userinfo = jwtUtil.getUserInfoFromToken(message.getToken().substring(7));
        String username = userinfo.getSubject();
        UserEntity user =  userRepository.findByName(username)
                .orElseThrow(() -> new CustomException(INVALID_TOKEN));

        return chatService.createChat(roomId, user, message.getMessage());
    }
}
