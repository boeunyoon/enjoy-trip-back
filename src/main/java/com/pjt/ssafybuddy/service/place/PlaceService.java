package com.pjt.ssafybuddy.service.place;

import com.pjt.ssafybuddy.entity.place.Place;

import java.sql.SQLException;
import java.util.List;

public interface PlaceService {
    List<Place> getPlacesByRegionAndCategory(String region, String category) throws SQLException;

    Place getPlaceById(int placeId) throws SQLException;

    void likePlace(int placeId, String userId);

    void unlikePlace(int placeId, String userId);

    boolean isPlaceLikedByUser(int placeId, String userId);
    List<Place> getMyLikedPlace(String userId);
}
