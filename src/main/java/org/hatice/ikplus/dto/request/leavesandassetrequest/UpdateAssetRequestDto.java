package org.hatice.ikplus.dto.request.leavesandassetrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.AssetStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssetRequestDto {
	private String name;
	private String serialNumber;
	private String assetType;
	private AssetStatus status;
	private LocalDateTime dueDate;
}