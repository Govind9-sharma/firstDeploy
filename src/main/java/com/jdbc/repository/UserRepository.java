package com.jdbc.repository;



import org.springframework.data.repository.CrudRepository;

import com.jdbc.entities.User;

public interface UserRepository extends CrudRepository<User,String>{
	
}
