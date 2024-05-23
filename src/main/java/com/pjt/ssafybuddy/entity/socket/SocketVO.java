package com.pjt.ssafybuddy.entity.socket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

// Data 어노테이션은 getter, setter를 자동 생성합니다.
@Data

// AllArgsConstructor 어노테이션은 생성자를 자동 생성합니다.
@AllArgsConstructor
@ToString
public class SocketVO {
    private String senderId;
    private String receiverId;
    private String content;
}