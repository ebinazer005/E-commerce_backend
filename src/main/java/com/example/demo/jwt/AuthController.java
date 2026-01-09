package com.example.demo.jwt;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.LoginRequest;
import com.example.demo.UserEntity;
import com.example.demo.UserRepo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtill jwtUtill;
    
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response) {

    	//email verification
    	UserEntity user = userRepo.findByEmail(request.getEmail()); 
    	
    	if (user == null) {
    		return ResponseEntity.badRequest().body(Map.of("message", "User not found"));
    	}
    	
    	 if (!user.isEmailVerified()) {
    	        return ResponseEntity.badRequest().body(Map.of("message", "Please verify your email first"));
    	  }
    	 
    	 
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtill.generateToken(userDetails);
        
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);    
        cookie.setSecure(true);      
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);   
        cookie.setAttribute("SameSite", "None");

        response.addCookie(cookie);

        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }
}
