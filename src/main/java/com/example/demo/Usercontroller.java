package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Usercontroller {
		
	@Autowired
	private UserService services;	
	
		@GetMapping("/home")
		private String pub() {
			return "home afer update";
		}
		
		@GetMapping("/demodata")
		private List<UserEntity> getData(){
			return services.demodata();
		}
		
		@PostMapping("/signin")
		public UserEntity signin(@RequestBody LoginRequest request) {
		    return services.adddata(request);
		}
		
		@GetMapping("/{id}")
		public String deleteData(@PathVariable long id) {
			services.deleteData(id);
			return "data deleted";
		}
		


}
