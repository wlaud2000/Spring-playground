package com.learning.springplayground.domain.chatting.controller;

import com.learning.springplayground.domain.chatting.dto.ChatRoomDto;
import com.learning.springplayground.domain.chatting.service.ChatRoomService;
import com.learning.springplayground.domain.chatting.service.ChatService;
import com.learning.springplayground.domain.user.entity.AuthUser;
import com.learning.springplayground.global.jwt.annotation.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatService;

    @PostMapping("/room")
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chatRoomService.createRoom(name);
    }

    @GetMapping("/rooms")
    public List<ChatRoomDto> findAllRooms() {
        return chatRoomService.findAllRooms();
    }

    @GetMapping("/room/{roomId}")
    public ChatRoomDto findRoomById(@PathVariable String roomId) {
        return chatRoomService.findRoomById(roomId);
    }
}
