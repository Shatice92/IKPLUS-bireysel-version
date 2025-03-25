package org.hatice.ikplus.dto.response.employeeresponse;

import org.hatice.ikplus.enums.EmployeeType;

import java.time.LocalDateTime;

public record EmployeeResponse(
		Long id,
		String position,
		EmployeeType status,
		LocalDateTime hireDate,
		String firstName,
		String lastName
) {
}