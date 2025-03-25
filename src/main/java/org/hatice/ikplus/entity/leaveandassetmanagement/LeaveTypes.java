package org.hatice.ikplus.entity.leaveandassetmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_leave_types")
public class LeaveTypes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private TypeLeaves leavesName;
	
	
	
	// Yeni Constructor
	public LeaveTypes(TypeLeaves leavesName) {
		this.leavesName = leavesName;
	}
}