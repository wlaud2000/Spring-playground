package com.learning.springplayground.domain.chatting.service;

import com.learning.springplayground.domain.chatting.dto.ChatRoomDto;
import com.learning.springplayground.domain.user.entity.AuthUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatRoomService {

    private final Map<String, ChatRoomDto> chatRoomMap = new ConcurrentHashMap<>();

    public ChatRoomDto createRoom(String name) {
        ChatRoomDto chatRoom = ChatRoomDto.create(name);
        chatRoomMap.put(chatRoom.roomId(), chatRoom);
        return chatRoom;
    }

    public List<ChatRoomDto> findAllRooms() {
        return List.copyOf(chatRoomMap.values());
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }
}
