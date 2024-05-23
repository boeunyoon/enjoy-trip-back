package com.pjt.ssafybuddy.entity.member;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String nickName;
    private String campus;
    private String refreshToken;
    private String accessToken;
    private String imageUrl;
}
