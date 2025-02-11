package org.hatice.ikplus.service.employeeservice;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeRequestDto;
import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.enums.EmployeeType;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.EmployeeMapper;
import org.hatice.ikplus.repository.employeerepository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	
	public void save(AddEmployeeRequestDto dto) {
		employeeRepository.save(EmployeeMapper.INSTANCE.fromAddEmployeeRequestDto(dto));
	}
	
	public Employee updateEmployee(UpdateEmployeeRequestDto dto) {
		Employee existingEmployeeEntity = employeeRepository.findById(dto.getUserId())
		                                                    .orElseThrow(() -> new EntityNotFoundException("Employee with id "
				                                                                                                   + dto.getUserId() + " not found"));
		
		
		EmployeeMapper.INSTANCE.updateEmployeeFromDto(dto, existingEmployeeEntity);
		
		return employeeRepository.save(existingEmployeeEntity);
	}
	
	
	public void activateEmployee(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isEmpty()) throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		Employee employee = optionalEmployee.get();
		employee.setStatus(EmployeeType.ACTIVE);
		employeeRepository.save(employee);
	}
	
	public void deactivateEmployee(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isEmpty()) throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		Employee employee = optionalEmployee.get();
		employee.setStatus(EmployeeType.PASSIVE);
		employeeRepository.save(employee);
	}
	
	public void delete(Long id) {
		Optional<Employee> byId = employeeRepository.findById(id);
		if (byId.isEmpty()) throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		employeeRepository.delete(byId.get());
	}
	
}