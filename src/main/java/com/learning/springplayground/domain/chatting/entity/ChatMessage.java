package com.learning.springplayground.domain.chatting.entity;

import com.learning.springplayground.domain.chatting.dto.ChatMessageDto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "chat_messages")  // 몽고DB에 저장될 컬렉션
public class ChatMessage {

    @Id
    private String id;  // 몽고DB의 고유 식별자
    private String roomId;
    private String sender;
    private String message;
    private ChatMessageDto.MessageType messageType;
    private long timestamp;  // 메시지 발송 시간

    public ChatMessageDto from() {
        return ChatMessageDto.builder()
                .roomId(roomId)
                .sender(sender)
                .message(message)
                .messageType(ChatMessageDto.MessageType.valueOf(messageType.toString()))
                .build();
    }
}
