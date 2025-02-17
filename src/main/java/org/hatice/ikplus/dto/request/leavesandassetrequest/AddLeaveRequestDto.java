package org.hatice.ikplus.dto.request.leavesandassetrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaveRequestDto {
	private Long employeeId;        // Çalışanın ID'si
	private Long leaveTypeId;       // İzin tipi ID'si
	private LocalDate startDate;    // Başlangıç tarihi
	private LocalDate endDate;      // Bitiş tarihi
	private Long approvedByUserId;  // Onaylayan kullanıcı ID'si
}