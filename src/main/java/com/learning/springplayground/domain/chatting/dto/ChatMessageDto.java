package com.learning.springplayground.domain.chatting.dto;

import lombok.Builder;

@Builder
public record ChatMessageDto (
        MessageType messageType,  // 메시지 타입 (입장, 대화)
        String roomId,            // 채팅방 ID
        String sender,            // 보낸 사람
        String message            // 메시지 내용
) {

    public enum MessageType {
        ENTER, TALK
    }

    // 새로운 메시지를 반환하는 메서드
    public ChatMessageDto withMessage(String newMessage) {
        return new ChatMessageDto(this.messageType, this.roomId, this.sender, newMessage);
    }
}
