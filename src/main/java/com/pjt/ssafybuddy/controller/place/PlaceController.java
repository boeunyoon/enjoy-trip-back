package com.pjt.ssafybuddy.controller.place;

import com.pjt.ssafybuddy.entity.place.Place;
import com.pjt.ssafybuddy.service.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlaceController {
    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    private final PlaceService placeService;

    @GetMapping("/search")
    public ResponseEntity<?> searchPlaces(@RequestParam("region") String region,
                                          @RequestParam("category") String category) {
        try {
            logger.info("Searching for places in region: {} and category: {}", region, category);
            List<Place> places = placeService.getPlacesByRegionAndCategory(region, category);
            if (places.isEmpty()) {
                return new ResponseEntity<>("No places found.", HttpStatus.NO_CONTENT);
            } else {
                logger.info("Places found: {}", places.size());
                return new ResponseEntity<>(places, HttpStatus.OK);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<?> getPlaceById(@PathVariable int placeId) {
        try {
            logger.info("Fetching place with ID: {}", placeId);
            Place place = placeService.getPlaceById(placeId);
            logger.info("Fetched place: {}", place);
            return new ResponseEntity<>(place, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping("/{placeId}/like")
    public ResponseEntity<Void> likePlace(@PathVariable int placeId, @RequestParam String userId) {
        placeService.likePlace(placeId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{placeId}/unlike")
    public ResponseEntity<Void> unlikePlace(@PathVariable int placeId, @RequestParam String userId) {
        placeService.unlikePlace(placeId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{placeId}/liked")
    public ResponseEntity<Boolean> isPlaceLikedByUser(@PathVariable int placeId, @RequestParam String userId) {
        boolean isLiked = placeService.isPlaceLikedByUser(placeId, userId);
        return ResponseEntity.ok(isLiked);
    }
    @GetMapping("/like/place/{userId}")
    public ResponseEntity<?> getMyLikedPlace(@PathVariable String userId){
        try{
            List<Place> result = placeService.getMyLikedPlace(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
