package com.pjt.ssafybuddy.entity.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    private int id;
    private int category;
    private String content;
    private String title;
    private String writer;
    private String writeDate;
    private int readCount;
    private int likeCount;
    private int commentCount;
}
