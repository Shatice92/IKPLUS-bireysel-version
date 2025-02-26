package org.hatice.ikplus.dto.response.userresponse;

import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.*;

import java.time.LocalDate;
import java.util.UUID;

public record UserProfileResponse(
		String firstName,
		String lastName,
		String email,
		UserGender gender,
		String phoneNumber,
		LocalDate birthDate,
		UserMaritalStatus maritalStatus,
		UserBloodType bloodType,
		String identificationNumber,
		String nationality,
		UserEducationLevel educationLevel,
		UserStatus status
) {
	
	}