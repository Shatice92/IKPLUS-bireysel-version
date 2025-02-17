package org.hatice.ikplus.dto.response.leavesandassetsresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.LeaveStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveResponse {
	private Long id;
	private Long employeeId;
	private Long leaveTypeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long approvedByUserId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LeaveStatus status;
}