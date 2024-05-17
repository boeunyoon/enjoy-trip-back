package com.pjt.ssafybuddy.service.place;

import com.pjt.ssafybuddy.entity.place.Place;

import java.sql.SQLException;
import java.util.List;

public interface PlaceService {

    public List<Place> getPlacesByRegionAndCategory(String region, String category) throws SQLException;

    public Place getPlaceById(int placeId) throws SQLException;
}
