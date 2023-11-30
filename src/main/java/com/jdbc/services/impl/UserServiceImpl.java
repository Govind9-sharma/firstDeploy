package com.jdbc.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jdbc.entities.Rating;
import com.jdbc.entities.User;
import com.jdbc.entity.Hotel;
import com.jdbc.exceptions.ResourceNotFoundException;
import com.jdbc.external.services.HotelService;
import com.jdbc.repository.UserRepository;
import com.jdbc.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
    private RestTemplate restTemplate;
//	private Logger logger=LoggerFactory.getLogger(UserService.class);
	@Override
	public User SaveUser(User user) {
		 String randomUserID=UUID.randomUUID().toString();
		 user.setUserId(randomUserID);
	     return userRepository.save(user);
	}
	@Override
	public List<User> getAllUser() {
		Iterable i= userRepository.findAll();
		Iterator <User>j=i.iterator();
		ArrayList<User> a=new ArrayList();
		while(j.hasNext())
		{
			a.add(j.next());
		}
		return a;
	}

	@Override
	public User getUser(String userId) {
		Rating []ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings",Rating[].class);
		List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList=ratings.stream().map(rating->{
//			ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
//			Hotel hotel=forEntity.getBody();
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		User u= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Id Not Found"));
		u.setRatings(ratingList);
		return u;
	}
}