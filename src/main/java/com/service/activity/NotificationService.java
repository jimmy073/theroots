package com.service.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.domain.people.User;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;
	
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification(User user) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUsername());
		mail.setFrom(sender);
		mail.setSubject("COnfirmation for Registration to Jimmy Sites");
		mail.setText("Hello " +user.getFirstName()+ " just to let you know that you registration is successful");

		javaMailSender.send(mail);
	}


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
	
}
