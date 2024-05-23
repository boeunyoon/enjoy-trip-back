package com.pjt.ssafybuddy.controller.socket;

import com.pjt.ssafybuddy.entity.socket.ChatMessage;
import com.pjt.ssafybuddy.entity.socket.SocketVO;
import com.pjt.ssafybuddy.service.socket.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class SocketController {
    private final ChatService chatService;
    // /receive를 메시지를 받을 endpoint로 설정합니다.
    @MessageMapping("/receive")
    // /send로 메시지를 반환합니다.
    @SendTo("/topic/messages")
    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
    public SocketVO SocketHandler(SocketVO socketVO) {
        // vo에서 getter로 userName을 가져옵니다.
        System.out.println(socketVO.getContent() + socketVO.getSenderId() + socketVO.getReceiverId());
        String sender = socketVO.getSenderId();
        String receiver = socketVO.getReceiverId();
        // vo에서 setter로 content를 가져옵니다.
        String content = socketVO.getContent();
        System.out.println();

        // 생성자로 반환값을 생성합니다.
        SocketVO result = new SocketVO(sender, receiver,content);
        // 반환
        return result;
    }

    @MessageMapping("/notify")
    // /send로 메시지를 반환합니다.
    @SendTo("/topic/notify")
    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    public Map SocketHandler(@RequestBody ChatMessage message) {
        // vo에서 getter로 userName을 가져옵니다.
        String reciverId = message.getReceiverId();
        String senderId = message.getSenderId();
        // vo에서 setter로 content를 가져옵니다.
        String content = message.getContent();
        Map<String, Object> resultMap = new HashMap<>();


        // 생성자로 반환값을 생성합니다.
        ChatMessage result = new ChatMessage(senderId, reciverId, content);

        chatService.sendMessage(senderId, reciverId, content);
        List<ChatMessage> list = chatService.getUnreadMessages(reciverId);
        int count = chatService.getUnreadMessageCount(reciverId);
        resultMap.put("msgInfo", result);
        resultMap.put("unReadMsg", list);
        resultMap.put("unReadMsgCount", count);

        // 반환
        return resultMap;
    }
    @GetMapping("/message/unread/count/{userId}")
    public ResponseEntity<?> getUnreadMessageCount(@PathVariable String userId){
        try{
            int count = chatService.getUnreadMessageCount(userId);
            return new ResponseEntity<>(count, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/message/list/received/{userId}")
    public ResponseEntity<?> getAllReceivedMessage(@PathVariable String userId){
        try{
            List<ChatMessage> result = chatService.getUserReceivedMessage(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping ("/message/list/unread/{userId}")
    public ResponseEntity<?> getUserReceivedUnReadMessage(@PathVariable String userId){
        try{
            List<ChatMessage> result = chatService.getUnreadMessages(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/message/list/send/{userId}")
    public ResponseEntity<?> getUserSendMessage(@PathVariable String userId){
        try{
            List<ChatMessage> result = chatService.getUserSendMessage(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/message/read/{id}")
    public ResponseEntity<?> makeUnreadToRead(@PathVariable int id){
        try{
            chatService.markMessagesAsRead(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/message/{id}")
    public ResponseEntity<?> getMessage(@PathVariable int id){
        try{
            ChatMessage message = chatService.getMessage(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}