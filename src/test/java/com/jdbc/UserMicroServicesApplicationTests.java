package com.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdbc.entities.Rating;
import com.jdbc.external.services.RatingService;

@SpringBootTest
class UserMicroServicesApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;
	
	@Test
	void createRating()
	{
		Rating rating=Rating.builder().rating(4).userId("").hotelId("").feedback("Good Overall").build();
		Rating savedRating=ratingService.createRating(rating);
	}
}
