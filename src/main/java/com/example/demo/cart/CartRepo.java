package com.example.demo.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.example.demo.UserEntity;


public interface CartRepo  extends JpaRepository<CartEntity, Long>{ 
		Optional<CartEntity>findByUser(UserEntity user);
}
