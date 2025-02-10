package org.hatice.ikplus.entity.shiftandbreakmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.ShiftType;

import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_shift")
public class Shift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long companyId;
	private Long employeeId;
	private ShiftType shiftType;
	private Timestamp startTime;
	private Timestamp endTime;


	
}