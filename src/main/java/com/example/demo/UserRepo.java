package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepo extends JpaRepository<UserEntity, Long>{

	UserEntity findByEmail(String email);
	boolean existsByEmail(String email);
	
	

}
