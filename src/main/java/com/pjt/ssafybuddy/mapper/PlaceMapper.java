package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.place.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlaceMapper {
    public List<Place> findPlacesByRegionAndCategory(@Param("region") String region, @Param("category") String category) throws SQLException;

    public Place findPlaceById(@Param("placeId") int placeId) throws SQLException;
}
