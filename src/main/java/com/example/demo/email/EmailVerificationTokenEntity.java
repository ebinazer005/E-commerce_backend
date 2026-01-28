package com.example.demo.email;

import java.time.LocalDateTime;

import com.example.demo.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EmailVerificationTokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	
		@OneToOne
	    @JoinColumn(name = "user_id")
	    private UserEntity user;
	    private LocalDateTime expiryDate;
	    
	    
	    
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public UserEntity getUser() {
			return user;
		}
		public void setUser(UserEntity user) {
			this.user = user;
		}
		public LocalDateTime getExpiryDate() {
			return expiryDate;
		}
		public void setExpiryDate(LocalDateTime expiryDate) {
			this.expiryDate = expiryDate;
		}
	    	
	    
	    
	
}
