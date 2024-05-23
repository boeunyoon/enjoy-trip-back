package com.pjt.ssafybuddy.controller.socket;

import com.pjt.ssafybuddy.entity.socket.ChatMessage;
import com.pjt.ssafybuddy.service.socket.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;
    private final ChatService chatService;
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessages(ChatMessage message){
        System.out.println(message);
        chatService.sendMessage(message.getSenderId(), message.getReceiverId(), message.getContent());
        return message;
    }
    @MessageMapping("/private")
    @SendToUser("/queue/private")
    public void sendPrivateMessage(ChatMessage message) {
        String receiverId = message.getReceiverId();
        System.out.println(message.getContent());
        System.out.println(message.getReceiverId());
        chatService.sendMessage(message.getSenderId(), message.getReceiverId(), message.getContent());
        template.convertAndSendToUser(message.getReceiverId().toString(), "/queue/private", message);
    }

    @GetMapping("/messages/unread/{receiverId}")
    public ResponseEntity<List<ChatMessage>> getUnreadMessages(@PathVariable String receiverId) {
        return ResponseEntity.ok(chatService.getUnreadMessages(receiverId));
    }

//    @PostMapping("/messages/read")
//    public ResponseEntity<Void> markMessagesAsRead(@RequestBody Map<String, String> ids) {
//        chatService.markMessagesAsRead(ids.get("receiverId"), ids.get("senderId"));
//        return ResponseEntity.ok().build();
//    }
}
