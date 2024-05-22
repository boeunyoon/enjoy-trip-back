package com.pjt.ssafybuddy.entity.place;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Place {
    private String addressName;
    private String categoryGroupCode;
    private String categoryGroupName;
    private int distance;
    private int id;
    private String phone;
    private String placeName;
    private String placeUrl;
    private String roadAddressName;
    private String x;
    private String y;
    private String region;
    private int placeId;
    private int likeCount;
}
