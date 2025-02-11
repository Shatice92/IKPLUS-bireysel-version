package org.hatice.ikplus.dto.response.employeeresponse;

import org.hatice.ikplus.enums.EmployeeType;

import java.time.LocalDateTime;

public record EmployeeResponse(
		Long id,
		Long userId,
		Long companyId,
		String position,
		EmployeeType status,
		LocalDateTime updatedAt
) {
}