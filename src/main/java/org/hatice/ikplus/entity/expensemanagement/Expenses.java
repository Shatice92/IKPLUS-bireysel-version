package org.hatice.ikplus.entity.expensemanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.ExpensesType;

import java.math.BigDecimal;
import java.time.LocalDate;

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
	private Long employeeId;
	private BigDecimal amount;
	private String description;
	private String receipt; // Fatura URL link veya IMAGE linki olcak.
	@Enumerated(EnumType.STRING)
	private ExpensesType status;
	private LocalDate submittedAt; // harcamanın yapıldığı zaman.
	private Long approvedByUserId;
	private LocalDate updateAt;
}