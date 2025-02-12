package org.hatice.ikplus.entity.shiftandbreakmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.shiftandbreakenums.BreakName;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_break")
public class Break {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long shiftId;
	
	@Enumerated(EnumType.STRING)
	private BreakName breakName;
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
}