package org.hatice.ikplus.entity.shiftandbreakmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.shiftandbreakenums.ShiftType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	private Long companyManagerId;
	private Long employeeId;
	
	@Enumerated(EnumType.STRING)
	private ShiftType shiftType;
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;


	
}