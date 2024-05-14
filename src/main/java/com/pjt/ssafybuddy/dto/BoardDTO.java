package com.pjt.ssafybuddy.dto;

import com.pjt.ssafybuddy.entity.board.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private int category;
    private String content;
    private String title;
    private String writer;
}
