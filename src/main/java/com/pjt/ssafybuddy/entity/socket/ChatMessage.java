package com.pjt.ssafybuddy.entity.socket;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {
    private Long id;
    private String senderId;
    private String receiverId;
    private String content;
    private String timestamp;
    private boolean readCheck;

    public ChatMessage(String senderId, String receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }
}