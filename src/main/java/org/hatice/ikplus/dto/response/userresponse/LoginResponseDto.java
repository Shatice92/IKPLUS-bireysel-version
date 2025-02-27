package org.hatice.ikplus.dto.response.userresponse;

import lombok.Data;

import org.hatice.ikplus.entity.usermanagement.Role;




@Data
public class LoginResponseDto {
	private String token;
	private Long userId;
	private Role role;  // Role nesnesi ekleniyor
}