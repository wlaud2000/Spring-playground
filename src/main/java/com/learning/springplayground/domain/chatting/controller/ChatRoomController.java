package com.learning.springplayground.domain.chatting.controller;

import com.learning.springplayground.domain.chatting.dto.ChatRoomDto;
import com.learning.springplayground.domain.chatting.service.ChatRoomService;
import com.learning.springplayground.domain.chatting.service.ChatService;
import com.learning.springplayground.domain.user.entity.AuthUser;
import com.learning.springplayground.domain.user.entity.User;
import com.learning.springplayground.domain.user.repository.UserRepository;
import com.learning.springplayground.global.jwt.annotation.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatService;
    private final UserRepository userRepository;

    @PostMapping("/room")
    public ChatRoomDto createRoom(@RequestParam String name, @CurrentUser AuthUser user) {
        return chatRoomService.createRoom(name, user);
    }

    // 유저가 채팅방에 참여
    @PostMapping("/room/{roomId}/join")
    public ResponseEntity<String> joinRoom(@PathVariable String roomId, @CurrentUser AuthUser user) {
        User user1 = userRepository.findByEmail(user.getEmail())
                        .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
        chatRoomService.addParticipantToRoom(roomId, user1);
        return ResponseEntity.ok("채팅방에 참여했습니다.");
    }

    // 유저가 참여한 채팅방 목록 조회
    @GetMapping("/user/rooms")
    public List<ChatRoomDto> getUserRooms(@CurrentUser AuthUser user) {
        return chatRoomService.findRoomsByUser(user);
    }
}
