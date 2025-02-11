package org.hatice.ikplus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.HolidayType;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_holiday")
public class Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private HolidayType holidayType;
	private LocalDate holidayDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String holidayName;
	
	
}