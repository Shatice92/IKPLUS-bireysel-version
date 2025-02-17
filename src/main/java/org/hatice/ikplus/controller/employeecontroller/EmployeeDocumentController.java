package org.hatice.ikplus.controller.employeecontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.employeeservice.EmployeeDocumentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Endpoints.COMPANY_MANAGER_EMPLOYEE_DOCUMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeDocumentController {
	private final EmployeeDocumentService employeeDocumentService;
}