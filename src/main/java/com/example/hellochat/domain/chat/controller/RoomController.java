package com.example.hellochat.domain.chat.controller;

import com.example.hellochat.domain.chat.domain.Chat;
import com.example.hellochat.domain.chat.domain.Room;
import com.example.hellochat.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final ChatService chatService;

    @GetMapping("/{roomId}")
    public List<Chat> getChats(@PathVariable(required = false) Long roomId) {
        List<Chat> chatList = chatService.findAllChatByRoomId(roomId);

        return chatList;
    }

    @PostMapping("/room")
    public void createRoom(@RequestBody RoomName room) {
        chatService.createRoom(room.getName());
    }

    @GetMapping("/roomList")
    public List<Room> roomList() {
        List<Room> roomList = chatService.findAllRoom();
        return roomList;
    }

}