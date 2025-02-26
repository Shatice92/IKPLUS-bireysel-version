package org.hatice.ikplus.dto.request.employeerequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDocumentRequestDto {
	private Long employeeId;
	private String name;
	private String url;
}