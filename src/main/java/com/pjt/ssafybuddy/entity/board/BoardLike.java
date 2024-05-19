package com.pjt.ssafybuddy.entity.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardLike {
    private String userId;
    private int boardId;
}
