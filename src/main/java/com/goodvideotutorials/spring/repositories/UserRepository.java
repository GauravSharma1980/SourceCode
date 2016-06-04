package com.goodvideotutorials.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goodvideotutorials.spring.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByName(String name);
	
}
