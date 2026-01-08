package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo repo;
	
	
		public List<UserEntity> demodata() {
			return repo.findAll();
		}
		
		
		public UserEntity adddata(LoginRequest request ) {

	        UserEntity user = new UserEntity();
	        user.setEmail(request.getEmail());

	        
	        user.setPassword(passwordEncoder.encode(request.getPassword()));

	       
	        user.setRole("USER");

	        return repo.save(user);
	    }
		
		
}
