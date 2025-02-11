package org.hatice.ikplus.service;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
}