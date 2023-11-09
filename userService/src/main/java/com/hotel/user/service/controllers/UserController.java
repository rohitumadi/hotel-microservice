package com.hotel.user.service.controllers;


import com.hotel.user.service.entities.User;
import com.hotel.user.service.services.UserService;
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

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = this.userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user=this.userService.getUserById(userId);
        return  new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users=this.userService.getAllUsers();
        return  new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


}
