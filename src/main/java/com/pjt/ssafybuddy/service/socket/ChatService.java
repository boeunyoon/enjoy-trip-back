package com.pjt.ssafybuddy.service.socket;

import com.pjt.ssafybuddy.entity.socket.ChatMessage;
import com.pjt.ssafybuddy.mapper.ChatMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMessageMapper chatMessageMapper;
    @Transactional
    public void sendMessage(String senderId, String receiverId, String content) {

        chatMessageMapper.saveMessage(senderId, receiverId, content);
    }

    public List<ChatMessage> getUnreadMessages(String receiverId) {
        return chatMessageMapper.findUnreadMessagesByReceiverId(receiverId);
    }

    @Transactional
    public void markMessagesAsRead(String receiverId, String senderId) {
        chatMessageMapper.markMessagesAsRead(senderId, receiverId);
    }
    public int getUnreadMessageCount(String receiverId){
        return chatMessageMapper.unreadMessageCount(receiverId);
    }
}
