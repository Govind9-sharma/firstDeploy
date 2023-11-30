package com.jdbc.external.services;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.jdbc.entity.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}