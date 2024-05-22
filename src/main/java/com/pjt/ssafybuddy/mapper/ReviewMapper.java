package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.review.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    Review getReviewById(@Param("id") int id);

    List<Review> getTopLikedReviewsByPlaceId(@Param("placeId") int placeId);

    List<Review> getAllReviewsByPlaceId(@Param("placeId") int placeId);

    List<Review> getReviewsByPlaceIdOrderByDate(@Param("placeId") int placeId);

    List<Review> getReviewsByPlaceIdOrderByLikes(@Param("placeId") int placeId);

    List<Review> getAllReviews(); // Add this method

    void addReview(Review review);

    void incrementLikes(@Param("id") int id);

    void decrementLikes(@Param("id") int id);

    void deleteReview(@Param("id") int id);

    void addLike(@Param("reviewId") int reviewId, @Param("userId") String userId);

    void removeLike(@Param("reviewId") int reviewId, @Param("userId") String userId);

    int checkIfUserLiked(@Param("reviewId") int reviewId, @Param("userId") String userId);
}
