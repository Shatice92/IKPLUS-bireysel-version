package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeDocumentRequestDto;
import org.hatice.ikplus.entity.employeemanagement.EmployeeDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeDocumentMapper {
	EmployeeDocumentMapper INSTANCE = Mappers.getMapper(EmployeeDocumentMapper.class);
	
	
	EmployeeDocument fromSaveEmployeeDocument(AddEmployeeDocumentRequestDto employeeDocument);
	
	
}