package com.pjt.ssafybuddy.entity.review;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    private int id;
    private String writer;
    private String title;
    private String content;
    private int placeId;
    private int likes;
    private LocalDateTime dateTime;
    private String imageUrl;
}
