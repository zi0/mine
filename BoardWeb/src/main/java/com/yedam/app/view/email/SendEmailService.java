package com.yedam.app.view.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	public void send(EmailVO vo) {
		String to = vo.getTo();// change accordingly
		String from = vo.getFrom(); // change accordingly
		final String username = "00lillllz@gmail.com"; // change accordingly
		final String password = "fujrjxvonusxyjag"; // change accordingly
		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject(vo.getSubject());
			// Now set the actual message
			message.setText(vo.getContent());
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully!");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

/*class MyAuth extends Authenticator {

	@Override 
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("aa", "bb");
	}
	
}*/
/*new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}*/