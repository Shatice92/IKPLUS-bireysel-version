package org.hatice.ikplus.service.usermanagement;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.repository.usermanagement.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
	
	private final UserRepository userRepository;
	private final JavaMailSender mailSender;
	private final PasswordEncoder passwordEncoder;
	
	public void createPasswordResetToken(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			String token = UUID.randomUUID().toString();
			user.setResetToken(token);
			user.setResetTokenExpiration(LocalDateTime.now().plusHours(1));
			userRepository.save(user);
			sendResetEmail(user.getEmail(), token);
		}
	}
	
	private void sendResetEmail(String email, String token) {
		String resetLink = "http://localhost:9090/reset-password.html?token=" + token;
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(email);
			helper.setSubject("Password Reset Request");
			helper.setText("Click the link to reset your password: " + resetLink, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public boolean resetPassword(String token, String newPassword) {
		Optional<User> userOptional = userRepository.findByResetToken(token);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getResetTokenExpiration().isAfter(LocalDateTime.now())) {
				user.setPassword(passwordEncoder.encode(newPassword));
				user.setResetToken(null);
				user.setResetTokenExpiration(null);
				userRepository.save(user);
				return true;
			}
		}
		return false;
	}
}