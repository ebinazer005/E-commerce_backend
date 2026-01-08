package com.example.demo.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtill {
	
	private static final String SECRET_KEY_STRING = "VOgzT1NxAzvNHqCkYFxXLiS5AuCXwg1S";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	
	public String generateToken(UserDetails userdetails) {
		return Jwts.builder()
		.subject(userdetails.getUsername()) // Subject (username)
		.issuedAt(new Date()) // Token issue time	
		.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
		.signWith(SECRET_KEY, Jwts.SIG.HS256) // HMAC-SHA256 signature
		.compact(); // Build token string
	}		
		
		
		// Validate JWT Token
		public boolean ValidateToken(String token, UserDetails userDetails) {
		return ExstractUserEmail(token).equals(userDetails.getUsername());
		}

		// Extract Username (Email) from JWT Token
		public String ExstractUserEmail(String token) {
		return Jwts.parser()
		.verifyWith(SECRET_KEY)
		.build()
		.parseSignedClaims(token)
		.getPayload()
		.getSubject();
		}
		}

