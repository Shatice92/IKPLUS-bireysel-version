package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeRequestDto;
import org.hatice.ikplus.dto.response.employeeresponse.EmployeeResponse;
import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
		ReportingPolicy.IGNORE)
public interface EmployeeMapper {
	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	
	@Mapping(target = "status", constant = "ACTIVE")
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	Employee fromAddEmployeeRequestDto(AddEmployeeRequestDto dto);
	
	@Mapping(target = "userId", source = "dto.userId")
	void updateEmployeeFromDto(UpdateEmployeeRequestDto dto, @MappingTarget Employee entity);
	
	EmployeeResponse toEmployeeResponse(Employee employee);
	
	
}