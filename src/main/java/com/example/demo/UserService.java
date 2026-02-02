package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.email.EmailRepo;
import com.example.demo.email.EmailService;
import com.example.demo.email.EmailVerificationTokenEntity;



@Service
public class UserService {
	

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmailRepo emailRepo;
	
	
	
		public List<UserEntity> demodata() {
			return repo.findAll();
		}
		
		
		public UserEntity adddata(LoginRequest request ) {
			
//			if (repo.existsByEmail(request.getEmail())) {
//	            throw new RuntimeException("Email already registered");
//	        }

	        UserEntity user = new UserEntity();
	        	
	        
	        
	        user.setEmail(request.getEmail());
	        user.setPassword(passwordEncoder.encode(request.getPassword()));
	        user.setRole("USER");
	        user.setEmailVerified(true);
//	        return repo.save(user);
	        user = repo.save(user);
	        
//	        String token = UUID.randomUUID().toString();
//	        
//	        EmailVerificationTokenEntity verification = new EmailVerificationTokenEntity();
//	        verification.setToken(token);
//	        verification.setUser(user);
//	        verification.setExpiryDate(LocalDateTime.now().plusMinutes(15));
//	        
//	        emailRepo.save(verification);
//	        emailService.sendverificationtoken(user.getEmail(), token);
//	        
	        return user;
	        
	        
	    }
		
		public void deleteData(long id) {
			repo.deleteById(id);
		}
		
		
}
