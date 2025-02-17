package org.hatice.ikplus.dto.request.companyrequest;

import jakarta.validation.constraints.NotNull;

public record CompanyRequestDto(
		@NotNull String name,
		@NotNull
		String emailDomain,
		@NotNull String logo,
		@NotNull Long companyManagerId) {
}