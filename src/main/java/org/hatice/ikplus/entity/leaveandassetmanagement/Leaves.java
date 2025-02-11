package org.hatice.ikplus.entity.leaveandassetmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.LeaveStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;
	
}