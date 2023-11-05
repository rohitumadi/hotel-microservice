package com.hotel.service.hotelService.services.impl;

import com.hotel.service.hotelService.entities.Hotel;
import com.hotel.service.hotelService.exceptions.ResourceNotFoundException;
import com.hotel.service.hotelService.repositories.HotelRepository;
import com.hotel.service.hotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomHotelId= UUID.randomUUID().toString();
        hotel.setHotelId(randomHotelId);
        Hotel newHotel = this.hotelRepository.save(hotel);
        return newHotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return this.hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel","hotel Id",hotelId));
    }
}
