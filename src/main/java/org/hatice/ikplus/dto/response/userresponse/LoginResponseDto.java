package org.hatice.ikplus.dto.response.userresponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.enums.RoleName;

import java.util.List;
import java.util.UUID;

@Data
public class LoginResponseDto {
	private String token;
	private Long userId;
	private Role role;  // Role nesnesi ekleniyor
}