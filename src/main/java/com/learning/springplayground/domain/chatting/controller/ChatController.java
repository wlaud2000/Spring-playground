package com.learning.springplayground.domain.chatting.controller;

import com.learning.springplayground.domain.chatting.dto.ChatMessageDto;
import com.learning.springplayground.domain.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        chatService.sendMessage(message);
    }

    // 새로운 메시지 불러오기 API
    @GetMapping("/chat/room/{roomId}/messages")
    public List<ChatMessageDto> getChatMessages(@PathVariable String roomId) {
        return chatService.getMessagesByRoomId(roomId);
    }
}
