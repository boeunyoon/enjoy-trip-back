package com.pjt.ssafybuddy.entity.group;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private int id;
    private String groupName;
    private String type;
    private String groupOwner;
    private int recruitMaxNumber;
    private String region;
    private String isOnline;
    private boolean isRecruiting;
    private String content;
}
