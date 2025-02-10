package org.hatice.ikplus.entity.leaveandassetmanagement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.AssetStatus;

import java.time.LocalDate;

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
	private LocalDate assignedDate;
	private String assetType;
	@Enumerated(EnumType.STRING )
	private AssetStatus status;
	private LocalDate dueDate;

}