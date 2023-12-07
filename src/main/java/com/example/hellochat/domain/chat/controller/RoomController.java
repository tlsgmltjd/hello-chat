package com.example.hellochat.domain.chat.controller;

import com.example.hellochat.domain.chat.entity.Chat;
import com.example.hellochat.domain.chat.entity.Room;
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
    public List<Chat> getChats(@PathVariable(required = false) Long roomId) {
        return chatService.findAllChatByRoomId(roomId);
    }

    @PostMapping("/room")
    public void createRoom(@RequestBody RoomCreateRequest request) {
        chatService.createRoom(request.getParticipates());
    }

    @GetMapping("/room")
    public List<Room> roomFind(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return chatService.findRoom(userDetails.getUser());
    }

}
