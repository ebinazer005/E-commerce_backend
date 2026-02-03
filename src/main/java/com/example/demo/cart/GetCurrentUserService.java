package com.example.demo.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.UserEntity;
import com.example.demo.UserRepo;

@Service
public class GetCurrentUserService {
		
	@Autowired	
	private UserRepo userRepo;

		
		public UserEntity getUserName() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				
			if (authentication == null || !authentication.isAuthenticated()
	                || "anonymousUser".equals(authentication.getPrincipal())) {
	            throw new RuntimeException("User not authenticated");
	        }

	        String email = authentication.getName();

	        UserEntity user = userRepo.findByEmail(email);

	        if (user == null) {
	            throw new RuntimeException("User not found in database");
	        }

	        return user;
		}
		
}
