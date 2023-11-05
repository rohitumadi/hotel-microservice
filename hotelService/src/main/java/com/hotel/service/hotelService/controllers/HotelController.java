package com.hotel.service.hotelService.controllers;

import com.hotel.service.hotelService.entities.Hotel;
import com.hotel.service.hotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private  HotelService hotelService;
    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {

        Hotel newHotel=this.hotelService.createHotel(hotel);
        return new ResponseEntity<Hotel>(newHotel, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotelList=this.hotelService.getAllHotels();
        return  new ResponseEntity<List<Hotel>>(hotelList,HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        Hotel hotel=this.hotelService.getHotelById(hotelId);
        return  new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
    }
}
