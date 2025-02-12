package org.hatice.ikplus.dto.request.userrequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
		@Email(message = "Email geçerli formatta değil.")
		@NotBlank
		String email,
		
		@Size(min = 8, max = 50, message = "Şifreniz en az 8 karakterden oluşmalıdır.")
		@NotBlank
		String password
		
) {
}