package org.hatice.ikplus.dto.request.employeerequest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDto {
	private String position;
	private Long userId;
	private Long companyId;
	
}