package com.movieshop.emailService;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.movieshop.model.User;

@Service
public class PrivateEmailService {

	public void sendEmail(User user) throws ClassNotFoundException, SQLException {

		final String username = "plamendanielpics@gmail.com";
		final String password = "8015680156plamen";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("plamendanielpics@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject("Password reset email");
			message.setText(
					"Dear, " + user.getName() + " " + user.getLastName() + " \nThis is your brand new password: "
							+ user.getCode() + " You can change it in the menu when logged in !");

			Transport.send(message);

			System.out.println("Email sent to: " + user.getName());

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
