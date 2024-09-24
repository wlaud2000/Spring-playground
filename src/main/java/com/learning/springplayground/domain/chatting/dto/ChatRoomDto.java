package com.learning.springplayground.domain.chatting.dto;

import com.learning.springplayground.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChatRoomDto {
    private final String roomId;
    private final String name;
    private final List<User> participants;

    // 정적 팩토리 메서드로 채팅방 생성
    public static ChatRoomDto create(String name) {
        return new ChatRoomDto(UUID.randomUUID().toString(), name, new ArrayList<>());
    }

    // 참가자 추가 메서드
    public void addParticipant(User user) {
        this.participants.add(user);  // 가변 리스트이므로 직접 수정 가능
    }

    // 특정 유저가 참여하고 있는지 확인
    public boolean hasParticipant(User user) {
        return this.participants.contains(user);
    }
}
