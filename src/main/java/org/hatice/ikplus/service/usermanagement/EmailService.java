package org.hatice.ikplus.service.usermanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender mailSender;
	
	public void sendVerificationEmail(String toEmail, String verificationLink) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("IKPlus - Email Verification");
		message.setText("Kaydınızı onaylamak için onay verin: " + verificationLink);
		message.setFrom("infoikplus@gmail.com"); // Burayı da güncelle
		
		mailSender.send(message);
	}
}