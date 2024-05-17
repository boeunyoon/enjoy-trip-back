package com.pjt.ssafybuddy.controller.place;

import com.pjt.ssafybuddy.entity.place.Place;
import com.pjt.ssafybuddy.service.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/search")
    public ResponseEntity<?> searchPlaces(@RequestParam("region") String region,
                                          @RequestParam("category") String category) {
        try {
            List<Place> places = placeService.getPlacesByRegionAndCategory(region, category);
            if (places.isEmpty()) {
                return new ResponseEntity<>("No places found.", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(places, HttpStatus.OK);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<?> getPlaceById(@PathVariable int placeId) {
        try {
            Place place = placeService.getPlaceById(placeId);
            return new ResponseEntity<>(place, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
