package org.hatice.ikplus.controller.employeecontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.service.employeeservice.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping()
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
}