package com.hotel.user.service.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private String hotelId;

    private String hotelName;
    private String hotelLocation;

    private String about;
}
