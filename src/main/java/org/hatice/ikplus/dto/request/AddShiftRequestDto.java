package org.hatice.ikplus.dto.request;

import lombok.Builder;
import lombok.Data;
import org.hatice.ikplus.enums.shiftandbreakenums.ShiftType;

import java.time.LocalDateTime;

@Data
@Builder
public class AddShiftRequestDto {
	
	Long employeeId; ShiftType shiftType;
	LocalDateTime startTime;
	LocalDateTime endTime;
	Long companyManagerId;
}