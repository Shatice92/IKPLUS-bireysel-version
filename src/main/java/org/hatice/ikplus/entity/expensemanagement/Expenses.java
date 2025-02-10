package org.hatice.ikplus.entity.expensemanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expenses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long employeeId; // Hangi çalışanın giderleri?
	private Double amount;
	private String description;
	private Double receipt;
	private Status status;
	private LocalDateTime submittedAt; // abonelik tarihi?
	private Long approvedByUserId; // Kim tarafından onaylandı?
	private LocalDate updateAt;
}