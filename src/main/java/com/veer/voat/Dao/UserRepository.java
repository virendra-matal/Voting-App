package com.veer.voat.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veer.voat.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsernameAndPassword(String username, String Password);
	User findByUsername(String name);

}
