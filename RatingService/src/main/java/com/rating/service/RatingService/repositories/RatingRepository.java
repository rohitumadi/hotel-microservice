package com.rating.service.RatingService.repositories;

import com.rating.service.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {
    //userId should be in Rating entity userId should be camel cased in method name
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
