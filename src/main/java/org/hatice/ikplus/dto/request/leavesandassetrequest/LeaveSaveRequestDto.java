package org.hatice.ikplus.dto.request.leavesandassetrequest;

import java.time.LocalDate;

public record LeaveSaveRequestDto(Long employeeId,
                                  Long leaveTypeId,
                                  LocalDate startDate,
                                  LocalDate endDate) {
}