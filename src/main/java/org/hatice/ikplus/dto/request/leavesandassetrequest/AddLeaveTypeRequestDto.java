package org.hatice.ikplus.dto.request.leavesandassetrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaveTypeRequestDto {
	private TypeLeaves leavesName;
}