package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.place.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlaceMapper {
    List<Place> findPlacesByRegionAndCategory(@Param("region") String region, @Param("category") String category) throws SQLException;

    Place findPlaceById(@Param("placeId") int placeId) throws SQLException;

    void incrementLikes(@Param("placeId") int placeId);

    void decrementLikes(@Param("placeId") int placeId);

    void addLike(@Param("placeId") int placeId, @Param("userId") String userId);

    void removeLike(@Param("placeId") int placeId, @Param("userId") String userId);

    int checkIfUserLiked(@Param("placeId") int placeId, @Param("userId") String userId);
    List<Place> selectMyLikedPlace(String userId);
}
