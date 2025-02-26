package org.hatice.ikplus.dto.request.userrequest;

import org.hatice.ikplus.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record UserUpdateRequestDto(
		UserGender gender,
		String phoneNumber,
		LocalDate birthDate,
		UserMaritalStatus maritalStatus,
		UserBloodType bloodType,
		String identificationNumber,
		String   nationality,
		UserEducationLevel educationLevel,
		UserStatus status,
		LocalDateTime updatedAt
) {
}