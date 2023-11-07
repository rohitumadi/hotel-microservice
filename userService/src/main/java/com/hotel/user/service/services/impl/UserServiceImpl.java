package com.hotel.user.service.services.impl;

import com.hotel.user.service.entities.Hotel;
import com.hotel.user.service.entities.Rating;
import com.hotel.user.service.entities.User;
import com.hotel.user.service.exceptions.ResourceNotFoundException;
import com.hotel.user.service.repositories.UserRepository;
import com.hotel.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {

        String ratingsURL="http://localhost:8083/ratings/users/{userId}";
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        Rating[] userRatings=restTemplate.getForObject(ratingsURL,Rating[].class,userId);

        List<Rating> ratingList = Arrays.stream(userRatings).toList();
        List<Rating> ratingsWithHotels = ratingList.stream().map(rating -> {
        String hotelsURL="http://localhost:8082/hotels/"+rating.getHotelId();

            ResponseEntity<Hotel> forEntity=restTemplate.getForEntity(hotelsURL, Hotel.class,userId);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code:{}",forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;


        }).toList();

        user.setRatings(ratingsWithHotels);

        return user;


    }
}
