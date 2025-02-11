package org.hatice.ikplus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDocumentService {
	
	private final org.hatice.ikplus.repository.EmployeeDocumentRepository EmployeeDocumentRepository;
}