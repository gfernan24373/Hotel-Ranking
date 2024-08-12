package com.ms.user.ms_user.configs;

import com.ms.user.ms_user.dto.HotelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="HOTEL-MS")
public interface IHotelServiceFeing {

    @GetMapping("/api/v1/hotel/{id}")
    HotelDTO getHotelById(@PathVariable("id")UUID id);
}
