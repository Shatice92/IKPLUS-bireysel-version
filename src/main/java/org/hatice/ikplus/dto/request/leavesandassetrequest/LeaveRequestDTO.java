package org.hatice.ikplus.dto.request.leavesandassetrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDTO {
	private Long leaveTypeId;
	private LocalDate startDate;
	private LocalDate endDate;
}