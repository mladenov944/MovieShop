package com.movieshop.emailService;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.movieshop.model.User;
import com.movieshop.model.UserDAO;

@Service
@EnableScheduling
public class EmailService {

	private static final String SUBJECT = "Promotion";

	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

	@Autowired
	private UserDAO dao;

	@Scheduled(cron = "0 0 0 25 12 ?")
	public void sendEmails() {

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

		List<User> users = dao.getSubscribedUsers();
		for (User user : users) {

			try {

				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress("plamendanielpics@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ivelin.mladenov@yahoo.com"));
				message.setSubject("Our new promotion is here");
				message.setText("Dear, " + user.getName() + " " + user.getLastName()
						+ " \ntoday we have special offer for you ! P" + "lease visit our site for more information !");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
