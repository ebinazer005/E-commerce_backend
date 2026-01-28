package com.example.demo.email;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfigurationHardcode {
	
	   @Bean
	    public JavaMailSender mailSender() {
	        JavaMailSenderImpl sender = new JavaMailSenderImpl();

	        sender.setHost("in-v3.mailjet.com");
	        sender.setPort(587);
	        sender.setUsername("0b5adc2cf50bd35adc0e4c097b8e7970"); // API KEY
	        sender.setPassword("10266d5382f00926a161b5b74679f0ab"); // SECRET KEY

	        Properties props = sender.getJavaMailProperties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.ssl.trust", "in-v3.mailjet.com");
	        props.put("mail.debug", "true"); // optional but useful

	        return sender;
	    }

}
