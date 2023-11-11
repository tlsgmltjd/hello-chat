package com.example.hellochat.domain.chat.controller;

import com.example.hellochat.domain.chat.entity.Chat;
import com.example.hellochat.domain.chat.entity.Room;
import com.example.hellochat.domain.chat.dto.RoomName;
import com.example.hellochat.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
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
    public void createRoom(@RequestBody RoomName room) {
        chatService.createRoom(room.getName());
    }

    @GetMapping("/rooms")
    public List<Room> roomList() {
        return chatService.findAllRoom();
    }

}
