package com.pjt.ssafybuddy.service.review;

import com.pjt.ssafybuddy.entity.review.Review;

import java.util.List;

public interface ReviewService {

    Review getReviewById(int id);

    List<Review> getTopLikedReviewsByPlaceId(int placeId);

    List<Review> getAllReviewsByPlaceId(int placeId);

    List<Review> getReviewsByPlaceIdOrderByDate(int placeId);

    List<Review> getReviewsByPlaceIdOrderByLikes(int placeId);

    List<Review> getAllReviews(); // Add this method

    void addReview(Review review);

    void likeReview(int reviewId, String userId);

    void unlikeReview(int reviewId, String userId);

    void deleteReview(int id);

    boolean isReviewLikedByUser(int reviewId, String userId);
}
