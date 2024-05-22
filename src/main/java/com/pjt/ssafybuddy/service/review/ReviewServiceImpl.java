package com.pjt.ssafybuddy.service.review;


import com.pjt.ssafybuddy.entity.review.Review;
import com.pjt.ssafybuddy.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    @Override
    public Review getReviewById(int id) {
        return reviewMapper.getReviewById(id);
    }

    @Override
    public List<Review> getTopLikedReviewsByPlaceId(int placeId) {
        return reviewMapper.getTopLikedReviewsByPlaceId(placeId);
    }

    @Override
    public List<Review> getAllReviewsByPlaceId(int placeId) {
        return reviewMapper.getAllReviewsByPlaceId(placeId);
    }

    @Override
    public List<Review> getReviewsByPlaceIdOrderByDate(int placeId) {
        return reviewMapper.getReviewsByPlaceIdOrderByDate(placeId);
    }

    @Override
    public List<Review> getReviewsByPlaceIdOrderByLikes(int placeId) {
        return reviewMapper.getReviewsByPlaceIdOrderByLikes(placeId);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewMapper.getAllReviews();
    }

    @Override
    public void addReview(Review review) {
        reviewMapper.addReview(review);
    }

    @Override
    public void likeReview(int reviewId, String userId) {
        reviewMapper.incrementLikes(reviewId);
        reviewMapper.addLike(reviewId, userId);
    }

    @Override
    public void unlikeReview(int reviewId, String userId) {
        reviewMapper.decrementLikes(reviewId);
        reviewMapper.removeLike(reviewId, userId);
    }

    @Override
    public void deleteReview(int id) {
        reviewMapper.deleteReview(id);
    }

    @Override
    public boolean isReviewLikedByUser(int reviewId, String userId) {
        return reviewMapper.checkIfUserLiked(reviewId, userId) > 0;
    }
}
