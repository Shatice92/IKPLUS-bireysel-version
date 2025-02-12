package org.hatice.ikplus.entity.employeemanagement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.EmployeeType;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long companyId;
	private String position;
	private LocalDate hireDate;
	@Enumerated(EnumType.STRING)
	private EmployeeType status;
	private LocalDateTime updatedAt;

}