package com.example.demo.cart;

import com.example.demo.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class CartEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private  Long id;
			
		@OneToOne(optional = false)
		@JoinColumn(name = "user_id" , nullable = false , unique = true)
		private UserEntity user;

		public Long getId() {
			return id;
		}

		public UserEntity getUser() {
			return user;
		}

		public void setUser(UserEntity user) {
			this.user = user;
		}
		
		
		
		

}
