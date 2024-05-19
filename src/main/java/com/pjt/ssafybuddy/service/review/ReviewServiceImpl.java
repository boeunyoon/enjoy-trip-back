package com.pjt.ssafybuddy.service.review;

import com.pjt.ssafybuddy.entity.review.Review;
import com.pjt.ssafybuddy.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

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
    public void addReview(Review review) {
        reviewMapper.addReview(review);
    }

    @Override
    public void likeReview(int reviewId, String userId) {
        if (reviewMapper.checkIfUserLiked(reviewId, userId) == 0) {
            reviewMapper.addLike(reviewId, userId);
            reviewMapper.incrementLikes(reviewId);
        }
    }

    @Override
    public void unlikeReview(int reviewId, String userId) {
        if (reviewMapper.checkIfUserLiked(reviewId, userId) > 0) {
            reviewMapper.removeLike(reviewId, userId);
            reviewMapper.decrementLikes(reviewId);
        }
    }

    @Override
    public void deleteReview(int id) {
        reviewMapper.deleteReview(id);
    }
}
