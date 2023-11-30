package com.jdbc.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jdbc.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	@PostMapping("/ratings")
	public Rating createRating(Rating values);
	
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId")String ratingId,Rating rating);
	
	@DeleteMapping("ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
}
