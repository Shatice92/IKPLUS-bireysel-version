package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.expenserequest.AddExpensesRequestDto;
import org.hatice.ikplus.dto.request.expenserequest.UpdateExpensesRequestDto;
import org.hatice.ikplus.dto.response.expenseresponse.ExpensesResponse;
import org.hatice.ikplus.entity.expensemanagement.Expenses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExpensesMapper {
	
	@Mapping(target = "status", constant = "SUBMITTED") // Varsayılan olarak SUBMITTED durumu atanıyor.
	@Mapping(target = "submittedAt", expression = "java(java.time.LocalDate.now())") // Günün tarihi atanıyor.
	Expenses fromAddExpensesRequestDto(AddExpensesRequestDto dto);
	
	@Mapping(target = "id", ignore = true) // ID güncellenemez
	@Mapping(target = "submittedAt", ignore = true) // Harcamanın ilk oluşturulma tarihi değiştirilemez
	void updateExpensesFromDto(UpdateExpensesRequestDto dto, @MappingTarget Expenses entity);
	
	ExpensesResponse toExpensesResponse(Expenses expenses);
}