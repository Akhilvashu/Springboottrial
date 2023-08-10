package com.mysql.trial.repository;

import com.mysql.trial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
   
	User findByUsername(String username);

	User deleteByUsername(String username);
	 
}


