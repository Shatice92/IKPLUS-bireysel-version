package org.hatice.ikplus.dto.request.userrequest;

public record UpdateUserRequestDto(
		String firstName,
		String lastName,
		String email,
		String password,
		String role
) {
}