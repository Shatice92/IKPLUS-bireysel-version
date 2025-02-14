package org.hatice.ikplus.dto.request.userrequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record RegisterRequestDto(
		@NotBlank String firstName,
		String lastName,
		@Email(message = "Email geçerli formatta değil.") String email,
		@Size(min = 8, max = 50, message = "Şifreniz en az 8 karakterden oluşmalıdır.")
		@NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%*^&+=]).{8,20}$",
				message = "Şifre kurallara uygun değil.") String password,
		String rePassword
) {
}