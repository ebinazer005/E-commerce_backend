package com.example.demo.email;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.email.EmailVerificationTokenEntity;
import com.example.demo.UserEntity;
import com.example.demo.UserRepo;

@Service
public class EmailTokenCleanupService {

	@Autowired 
	private EmailRepo emailRepo;
	
	@Autowired 
	UserRepo userRepo;
	
	@Scheduled(fixedRate = 90000)
	public void deleteExpiryToken() {
		
		LocalDateTime currentTime = LocalDateTime.now();
		
		 var expiryTokens = emailRepo.findAll()
				.stream()
				.filter(t->t.getExpiryDate().isBefore(currentTime))
				.toList();
		
		 for (EmailVerificationTokenEntity token : expiryTokens) {
				
			 emailRepo.delete(token);
			 
			 UserEntity user = token.getUser();
			 
			 if(!user.isEmailVerified()) {
				 userRepo.delete(user);
			 }
		}
	}
	
	
	
	
}
