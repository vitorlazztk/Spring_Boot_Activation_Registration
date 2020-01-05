package com.sec.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sec.entity.User;

@Service
public class EmailService {
    private final Log log = LogFactory.getLog(this.getClass());
    
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendMessage(User user) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(user.getEmail());
			message.setSubject("Sikeres regisztrálás");
			message.setText("Kedves " + user.getFullName() + "! \n \n Köszönjük, hogy regisztráltál az oldalunkra!"
					+ " \n \n Az aktiváláshoz kérlek kattints az alábbi linkre: "
					+ " \n \n http://104.248.83.138:8090/activation/" + user.getActivation());
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Hiba e-mail küldéskor az alábbi címre: " + user.getEmail() + "  " + e);
		}
		

	}
	
	
}
