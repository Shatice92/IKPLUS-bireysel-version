package org.hatice.ikplus.service.employeeservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeDocumentRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeDocumentRequestDto;
import org.hatice.ikplus.dto.response.employeeresponse.EmployeeDocumentResponseDto;
import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.entity.employeemanagement.EmployeeDocument;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.EmployeeDocumentMapper;
import org.hatice.ikplus.mapper.EmployeeMapper;
import org.hatice.ikplus.repository.employeerepository.EmployeeDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeDocumentService {
	
	private final EmployeeDocumentRepository EmployeeDocumentRepository;
	private final EmployeeDocumentMapper EmployeeDocumentMapper;
	private final EmployeeDocumentRepository employeeDocumentRepository;
	
	public void save(AddEmployeeDocumentRequestDto dto){
		EmployeeDocument employeeDocument = EmployeeDocumentMapper.fromSaveEmployeeDocument(dto);
		EmployeeDocumentRepository.save(employeeDocument);
	}
	
	public EmployeeDocumentResponseDto updateEmployeeDocument(Long id, UpdateEmployeeDocumentRequestDto dto) {
		EmployeeDocument employeeDocument =
				EmployeeDocumentRepository.findById(id).orElseThrow(() -> new IKPlusException(ErrorType.EMPLOYEEDOCUMENT_NOT_FOUND));
		employeeDocument.setName(dto.getName());
		employeeDocument.setEmployeeId(dto.getEmployeeId());
		employeeDocument.setUrl(dto.getUrl());
		EmployeeDocumentRepository.save(employeeDocument);
		
		return new EmployeeDocumentResponseDto(employeeDocument.getId(),employeeDocument.getEmployeeId(),
		                                       employeeDocument.getName(),employeeDocument.getUrl(),
		                                       employeeDocument.getUploadedDate());
	
	}
	
	public void delete(Long id) {
		Optional<EmployeeDocument> byId = employeeDocumentRepository.findById(id);
		if (byId.isEmpty()) throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		employeeDocumentRepository.delete(byId.get());
	}
}