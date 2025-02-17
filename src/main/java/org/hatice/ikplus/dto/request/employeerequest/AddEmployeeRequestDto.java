package org.hatice.ikplus.dto.request.employeerequest;

import jakarta.validation.constraints.NotNull;
import org.hatice.ikplus.enums.EmployeeType;

import java.time.LocalDate;

public record AddEmployeeRequestDto(
		@NotNull
		Long userId,
		@NotNull
		Long companyId,
		@NotNull
		String position,
		@NotNull
		LocalDate hireDate
) {

}