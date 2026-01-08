package com.example.demo;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class UserEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String email;
		private String password;
		private String role;
		
		public UserEntity(long id, String email, String password, String role) {
			super();
			this.id = id;
			this.email = email;
			this.password = password;
			this.role = role;
		}

		public UserEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
	

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
		
		
		
}
