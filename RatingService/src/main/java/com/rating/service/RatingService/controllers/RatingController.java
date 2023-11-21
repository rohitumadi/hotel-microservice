package com.rating.service.RatingService.controllers;


import com.rating.service.RatingService.entities.Rating;
import com.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating newRating= this.ratingService.createRating(rating);
        return new ResponseEntity<Rating>(newRating, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getALlRatings(){
        List<Rating> allRatings= this.ratingService.getAllRatings();
        return new ResponseEntity<List<Rating>>(allRatings,HttpStatus.OK);
    }
    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId)
    {
        List<Rating> ratings=this.ratingService.getRatingByUserId(userId);
        return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId)
    {
        List<Rating> ratings=this.ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK);
    }
}
