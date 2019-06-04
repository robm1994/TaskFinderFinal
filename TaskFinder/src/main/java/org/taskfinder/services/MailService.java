package org.taskfinder.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(HttpSession session, String description, String date) throws MailException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("taskfinderconfirmation@gmail.com");
		mail.setSubject("Offer Confirmation");
		mail.setText("Thank You for using TaskFinder!"
				+ " Your Offer for:" + description + 
				"on the :"  + date  + "has been posted");
		javaMailSender.send(mail);
	}

	
	
	public void asendMail(String email_worker, String task1 ) throws MailException {
		

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email_worker);
		mail.setFrom("taskfinderconfirmation@gmail.com");
		mail.setSubject("Offer Acceptance");
		mail.setText("Thank You for using TaskFinder,"+ " Your Offer has been accepted for " + task1 +"!");
		javaMailSender.send(mail);
	}

	
	
	

	
	/*
	public void sendMailAccepted(HttpSession session) throws MailException {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//String email = auth.getName();

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo();
		mail.setFrom("taskfinderconfirmation@gmail.com");
		mail.setSubject("Offer accepted");
		mail.setText("An Offer you have made has been accepted, Login to view");
		javaMailSender.send(mail);
	}*/

}
