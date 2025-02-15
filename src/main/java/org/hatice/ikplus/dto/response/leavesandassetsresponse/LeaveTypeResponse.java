package org.hatice.ikplus.dto.response.leavesandassetsresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeResponse {
	private Long id;
	private TypeLeaves leavesName;
}