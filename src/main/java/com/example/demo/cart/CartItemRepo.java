package com.example.demo.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CartItemRepo extends JpaRepository<CartItemEntity, Long> {
	
	Optional<CartItemEntity> findByCartAndProductId(CartEntity cart, Long produId);
	
	List<CartItemEntity> findByCart(CartEntity cart);

	
}
