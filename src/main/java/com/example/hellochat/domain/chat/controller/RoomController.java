package com.example.hellochat.domain.chat.controller;

import com.example.hellochat.domain.chat.dto.ChatResponse;
import com.example.hellochat.domain.chat.dto.RoomResponse;
import com.example.hellochat.domain.chat.entity.Chat;
import com.example.hellochat.domain.chat.dto.RoomCreateRequest;
import com.example.hellochat.domain.chat.service.ChatService;
import com.example.hellochat.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final ChatService chatService;


    @GetMapping("/chat/{roomId}")
    public List<ChatResponse> getChats(@PathVariable(required = false) Long roomId) {
        return chatService.findAllChatByRoomId(roomId);
    }

    @PostMapping("/room")
    public void createRoom(@RequestBody RoomCreateRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        chatService.createRoom(userDetails.getUser().getUsersId(), request.getFromUser());
    }

    @GetMapping("/room")
    public List<RoomResponse> roomFind(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatService.findRoom(userDetails.getUser());
    }

}
