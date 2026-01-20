package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepo extends JpaRepository<EmailVerificationTokenEntity , Long> {

    EmailVerificationTokenEntity findByToken(String token);
    

}
