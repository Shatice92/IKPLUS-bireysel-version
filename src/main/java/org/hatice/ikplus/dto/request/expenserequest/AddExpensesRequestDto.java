package org.hatice.ikplus.dto.request.expenserequest;

import java.math.BigDecimal;

public record AddExpensesRequestDto(
		Long employeeId,
		BigDecimal amount,
		String description,
		String receipt
) {}