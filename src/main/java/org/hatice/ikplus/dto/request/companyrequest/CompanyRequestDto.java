package org.hatice.ikplus.dto.request.companyrequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompanyRequestDto(@NotBlank String name, @NotBlank String emailDomain, @NotBlank String logo,
                                @NotNull Long companyManagerId) {
}