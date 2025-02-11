package org.hatice.ikplus.service.employeeservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.employeerepository.EmployeeRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
}