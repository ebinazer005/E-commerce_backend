package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${app.base-url}")
	private String baseurl;
	
	public void sendverificationtoken(String mail , String token) {
		
		String link = baseurl + "/verify-email?token=" + token; 
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(mail);
		message.setSubject("verification mail from ebi store");
		message.setText("Click the verification link "+link);
		
		javaMailSender.send(message);
		
	}

}
