package com.jdbc.services;

import java.util.List;

import com.jdbc.entities.User;

public interface UserService {
	User SaveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
}
