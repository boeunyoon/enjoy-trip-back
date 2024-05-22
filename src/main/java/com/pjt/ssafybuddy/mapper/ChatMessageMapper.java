package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.socket.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    void saveMessage(@Param("senderId")String senderId, @Param("receiverId")String receiverId, @Param("content")String content);
    List<ChatMessage> findUnreadMessagesByReceiverId(String receiverId);
    void markMessagesAsRead(int id);
    int unreadMessageCount(String receiverId);
    List<ChatMessage> findAllUserMessage(String receiverId);
    List<ChatMessage> findAllUserSendMessage(String senderId);
    ChatMessage findMessageById(int id);
}