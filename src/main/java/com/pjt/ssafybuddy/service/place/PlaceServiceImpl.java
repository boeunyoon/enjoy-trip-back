package com.pjt.ssafybuddy.service.place;

import com.pjt.ssafybuddy.entity.place.Place;
import com.pjt.ssafybuddy.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public List<Place> getPlacesByRegionAndCategory(String region, String category) throws SQLException {
        return placeMapper.findPlacesByRegionAndCategory(region, category);
    }

    @Override
    public Place getPlaceById(int placeId) throws SQLException {
        return placeMapper.findPlaceById(placeId);
    }

    @Override
    public void likePlace(int placeId, String userId) {
        placeMapper.incrementLikes(placeId);
        placeMapper.addLike(placeId, userId);
    }

    @Override
    public void unlikePlace(int placeId, String userId) {
        placeMapper.decrementLikes(placeId);
        placeMapper.removeLike(placeId, userId);
    }

    @Override
    public boolean isPlaceLikedByUser(int placeId, String userId) {
        return placeMapper.checkIfUserLiked(placeId, userId) > 0;
    }

    @Override
    public List<Place> getMyLikedPlace(String userId) {
        return placeMapper.selectMyLikedPlace(userId);
    }
}
