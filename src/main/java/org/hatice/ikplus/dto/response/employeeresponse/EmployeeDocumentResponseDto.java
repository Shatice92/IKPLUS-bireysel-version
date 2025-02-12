package org.hatice.ikplus.dto.response.employeeresponse;

import java.time.LocalDateTime;

public record EmployeeDocumentResponseDto(Long id, Long employeeId, String name, String url,
                                          LocalDateTime uploadedDate) {
}