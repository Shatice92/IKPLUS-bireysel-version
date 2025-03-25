package org.hatice.ikplus.dto.response.companyresponse;

import org.hatice.ikplus.enums.CompanyStatus;

public record CompanyResponse(Long id, String name, String emailDomain, String logo, CompanyStatus status,Long companyManagerId,Long subscriptionId
                              ) {
}