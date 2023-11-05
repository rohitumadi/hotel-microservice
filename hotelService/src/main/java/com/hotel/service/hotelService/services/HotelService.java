package com.hotel.service.hotelService.services;

import com.hotel.service.hotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
     Hotel createHotel(Hotel hotel);

    ///getall
    List<Hotel> getAllHotels();

    //get single
    Hotel getHotelById(String hotelId);
}
