package com.ms.hotel.service;

import com.ms.hotel.dto.HotelDTO;
import com.ms.hotel.model.HotelEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IHotelService {

    ResponseEntity<List<HotelEntity>> getAll();

    ResponseEntity<HotelEntity> create(HotelDTO hotelDTO);

    ResponseEntity<HotelEntity> getById(UUID id);

    void deleteById(UUID id);

    ResponseEntity<HotelEntity> updateById(HotelDTO hotelDto,UUID id);

}
