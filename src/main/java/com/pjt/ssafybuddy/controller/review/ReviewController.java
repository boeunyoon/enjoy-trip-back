package com.pjt.ssafybuddy.controller.review;

import com.pjt.ssafybuddy.entity.review.Review;
import com.pjt.ssafybuddy.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable int id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/place/{placeId}/top-liked")
    public ResponseEntity<List<Review>> getTopLikedReviewsByPlaceId(@PathVariable int placeId) {
        List<Review> reviews = reviewService.getTopLikedReviewsByPlaceId(placeId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/place/{placeId}")
    public ResponseEntity<List<Review>> getAllReviewsByPlaceId(@PathVariable int placeId) {
        List<Review> reviews = reviewService.getAllReviewsByPlaceId(placeId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/place/{placeId}/date")
    public ResponseEntity<List<Review>> getReviewsByPlaceIdOrderByDate(@PathVariable int placeId) {
        List<Review> reviews = reviewService.getReviewsByPlaceIdOrderByDate(placeId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/place/{placeId}/likes")
    public ResponseEntity<List<Review>> getReviewsByPlaceIdOrderByLikes(@PathVariable int placeId) {
        List<Review> reviews = reviewService.getReviewsByPlaceIdOrderByLikes(placeId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/place/{placeId}/{sortOrder}")
    public ResponseEntity<List<Review>> getAllReviewsByPlaceId(@PathVariable int placeId, @PathVariable String sortOrder) {
        List<Review> reviews;
        if ("likes".equals(sortOrder)) {
            reviews = reviewService.getReviewsByPlaceIdOrderByLikes(placeId);
        } else {
            reviews = reviewService.getReviewsByPlaceIdOrderByDate(placeId);
        }
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody Review review) {
        reviewService.addReview(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{reviewId}/like")
    public ResponseEntity<Void> likeReview(@PathVariable int reviewId, @RequestParam String userId) {
        reviewService.likeReview(reviewId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{reviewId}/unlike")
    public ResponseEntity<Void> unlikeReview(@PathVariable int reviewId, @RequestParam String userId) {
        reviewService.unlikeReview(reviewId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{reviewId}/liked")
    public ResponseEntity<Boolean> isReviewLikedByUser(@PathVariable int reviewId, @RequestParam String userId) {
        boolean isLiked = reviewService.isReviewLikedByUser(reviewId, userId);
        return ResponseEntity.ok(isLiked);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
