package org.hatice.ikplus.dto.request.employeerequest;

import org.hatice.ikplus.enums.EmployeeType;

import java.time.LocalDate;

public record AddEmployeeRequestDto(
		Long userId,
		Long companyId,
		String position,
		LocalDate hireDate
) {

}