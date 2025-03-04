package org.hatice.ikplus.entity.leaveandassetmanagement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.AssetStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_assets")
public class Assets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long employeeId;
	private String name;
	private String serialNumber;
	private LocalDateTime assignedDate;
	private String assetType;
	@Enumerated(EnumType.STRING )
	private AssetStatus status;
	private LocalDateTime dueDate;
	private Long companyManagerId;

}