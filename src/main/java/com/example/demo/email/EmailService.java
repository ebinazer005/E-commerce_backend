package com.example.demo.email;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	@Value("${app.base-url}")
	private String baseurl = "https://e-commerce-backend-01vu.onrender.com";
	
//	@Value("${app.mail.from}")
//	private String fromMail;
	
	private String fromMail = "ebinazer206@gmail.com";
	
	public void sendverificationtoken(String mail , String token) {
		
		String link = baseurl + "/verify-email?token=" + token; 
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(mail);
		message.setFrom(fromMail);
		message.setSubject("verification mail from ebi store");
		message.setText("Click the verification link "+link);
		
		try {
			javaMailSender.send(message);
			System.out.println("Mail successfully send " + mail);
		} catch (Exception e) {
			System.out.println("fail to send mail " + e.getMessage());
		}
			
	}

}
