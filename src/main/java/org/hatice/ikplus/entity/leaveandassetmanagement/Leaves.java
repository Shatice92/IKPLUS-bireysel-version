package org.hatice.ikplus.entity.leaveandassetmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.LeaveStatus;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_leaves")
public class Leaves {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long employeeId;
	private Long leaveTypeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long approvedByUserId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;
	
}