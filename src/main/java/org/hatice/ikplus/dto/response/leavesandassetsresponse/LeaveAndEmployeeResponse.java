package org.hatice.ikplus.dto.response.leavesandassetsresponse;

import org.hatice.ikplus.enums.leaveandassetenums.LeaveStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LeaveAndEmployeeResponse(
		Long employeeId,
		String position,
		Long leaveTypeId,
		LocalDate startDate,
		LocalDate endDate,
		LeaveStatus status
) { }