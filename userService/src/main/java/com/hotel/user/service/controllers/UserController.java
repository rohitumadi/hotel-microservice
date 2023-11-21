package com.hotel.user.service.controllers;


import com.hotel.user.service.entities.User;
import com.hotel.user.service.services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = this.userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

    }

//    int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        logger.info("Get Single User Handler: UserController");
//        logger.info("Retry count: {}",retryCount);
//        retryCount++;
        User user=this.userService.getUserById(userId);
        return  new ResponseEntity<User>(user, HttpStatus.OK);
    }


    public ResponseEntity<User> ratingHotelFallback( String userId,Exception ex)
    {
        logger.info("Fallback is executed because some service is down",ex.getMessage());
        ex.printStackTrace();
        User user=User.builder().userEmail("dummy@gmail.com")
                .userName("dummy")
                .about("created as dummy because some service is down")
                .userId("12345").build();

        return new ResponseEntity<>(user,HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users=this.userService.getAllUsers();
        return  new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


}
