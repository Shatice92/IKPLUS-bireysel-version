package org.hatice.ikplus.dto.response.companyresponse;

import org.hatice.ikplus.enums.CompanyStatus;

public record CompanyResponse(Long id, String nameame, String emailDomain, String logo, CompanyStatus status,
                              boolean isApproved) {
}