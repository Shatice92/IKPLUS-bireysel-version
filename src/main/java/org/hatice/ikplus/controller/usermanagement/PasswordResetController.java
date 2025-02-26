package org.hatice.ikplus.controller.usermanagement;


import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.service.usermanagement.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping(PASSWORD)
@RequiredArgsConstructor
@CrossOrigin("*")
public class PasswordResetController {
	
	private final PasswordResetService passwordResetService;
	
	@PostMapping(PASSWORD_REQUEST)
	public ResponseEntity<BaseResponse<String>> requestPasswordReset(@RequestParam String email) {
		passwordResetService.createPasswordResetToken(email);
		return ResponseEntity.ok(BaseResponse.<String>builder().success(true)
		                                     .message("Şifre yenileme işlemi mail adresinize gönderildi").code(200)
		                                     .data(null).build());
	}
	
	@PostMapping(PASSWORD_RESET)
	public ResponseEntity<BaseResponse<String>> resetPassword(@RequestParam String token,
	                                                          @RequestParam String newPassword) {
		boolean result = passwordResetService.resetPassword(token, newPassword);
		if (result) {
			return ResponseEntity.ok(BaseResponse.<String>builder().success(true).message("Şifre başarıyla yenilendi")
			                                     .code(200).data(null).build());
		}
		else {
			return ResponseEntity.badRequest().body(BaseResponse.<String>builder().success(false)
			                                                    .message("Invalid or expired token.").code(400)
			                                                    .data(null).build());
		}
	}
}