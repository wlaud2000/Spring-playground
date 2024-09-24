package com.learning.springplayground.domain.chatting.service;

import com.learning.springplayground.domain.chatting.dto.ChatRoomDto;
import com.learning.springplayground.domain.user.entity.AuthUser;
import com.learning.springplayground.domain.user.entity.User;
import com.learning.springplayground.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    private final Map<String, ChatRoomDto> chatRoomMap = new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    public ChatRoomService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ChatRoomDto createRoom(String name, AuthUser creator) {
        ChatRoomDto chatRoom = ChatRoomDto.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        // 방 생성자를 참가자로 추가
        User user = userRepository.findByEmail(creator.getEmail())
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
        addParticipantToRoom(chatRoom.getRoomId(), user);

        return chatRoom;
    }

    // 특정 방에 유저 추가 메서드
    public void addParticipantToRoom(String roomId, User user) {
        User foundUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
        ChatRoomDto room = chatRoomMap.get(roomId);  // 해당 방을 찾아서
        if (room != null) {
            room.addParticipant(foundUser);  // 참가자 리스트에 유저 추가
        } else {
            throw new RuntimeException("해당 방이 존재하지 않습니다.");
        }
    }

    // 유저가 참여한 방 목록 조회
    public List<ChatRoomDto> findRoomsByUser(AuthUser user) {
        User foundUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));

        return chatRoomMap.values().stream()
                .filter(room -> room.hasParticipant(foundUser))
                .collect(Collectors.toList());
    }


}
