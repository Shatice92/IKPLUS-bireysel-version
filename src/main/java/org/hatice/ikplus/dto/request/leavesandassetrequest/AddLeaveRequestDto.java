package org.hatice.ikplus.dto.request.leavesandassetrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaveRequestDto {
	private Long employeeId;
	private Long leaveTypeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createdAt;
}