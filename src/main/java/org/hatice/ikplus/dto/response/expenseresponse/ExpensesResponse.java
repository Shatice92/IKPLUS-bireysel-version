package org.hatice.ikplus.dto.response.expenseresponse;

import org.hatice.ikplus.enums.ExpensesType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpensesResponse(
		Long id,
		Long employeeId,
		BigDecimal amount,
		String description,
		String receipt,
		ExpensesType status,
		LocalDate submittedAt,
		Long approvedByUserId,
		LocalDate updatedAt
) {}