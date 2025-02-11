package org.hatice.ikplus.service.employeeservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.employeerepository.EmployeeDocumentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDocumentService {
	
	private final EmployeeDocumentRepository EmployeeDocumentRepository;
}