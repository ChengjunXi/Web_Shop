// package com.chengjunxi.webshop.order;


// import org.springframework.stereotype.Service;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.MailException;

// @Service
// public class MailNotification {

// 	@Autowired
// 	private JavaMailSender javaMailSender;

// 	@Async
// 	public void sendNotificaitoin(String email) throws MailException, InterruptedException {
//         SimpleMailMessage mail = new SimpleMailMessage();
// 		mail.setTo(email);
// 		mail.setFrom("feint4994@gmail.com");
// 		mail.setSubject("Order Confirmation");
// 		mail.setText("Thanks for your purchase. Your payment is successful");
// 		javaMailSender.send(mail);
// 	}
// }
