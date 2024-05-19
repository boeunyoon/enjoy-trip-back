package com.pjt.ssafybuddy.entity.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private String writer;
    private int boardId;
    private String content;
    private String writeDate;
}
