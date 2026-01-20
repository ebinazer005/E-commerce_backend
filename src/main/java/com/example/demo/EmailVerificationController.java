package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailVerificationController {

    @Autowired
    private EmailRepo emailRepo;

    @Autowired
    private UserRepo userRepo;
    
  

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String token) {

        EmailVerificationTokenEntity tokenEntity = emailRepo.findByToken(token);
        
        

        if (tokenEntity == null) {
            return "Invalid verification link";
        }

        
        if (tokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
        	
        	
        	
            return "Verification link expired";
            
        }

        UserEntity user = tokenEntity.getUser();
        user.setEmailVerified(true);
        userRepo.save(user);

        emailRepo.delete(tokenEntity);

        return "Email verified successfully";
    }
}

