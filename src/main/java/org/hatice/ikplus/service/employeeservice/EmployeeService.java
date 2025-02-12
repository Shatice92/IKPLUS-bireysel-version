package org.hatice.ikplus.service.employeeservice;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeRequestDto;
import org.hatice.ikplus.dto.response.employeeresponse.EmployeeResponse;
import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.enums.EmployeeType;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.EmployeeMapper;
import org.hatice.ikplus.repository.employeerepository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	
	public void save(AddEmployeeRequestDto dto) {
		employeeRepository.save(EmployeeMapper.INSTANCE.fromAddEmployeeRequestDto(dto));
	}
	
	public EmployeeResponse updateEmployee(Long id,UpdateEmployeeRequestDto dto) {
		
		Employee existingEmployeeEntity = employeeRepository.findById(id)
		                                                    .orElseThrow(() -> new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND));
		
		EmployeeMapper.INSTANCE.updateEmployeeFromDto(dto, existingEmployeeEntity);
		
		
		Employee savedEmployee = employeeRepository.save(existingEmployeeEntity);
		
		return EmployeeMapper.INSTANCE.toEmployeeResponse(savedEmployee);
	}
	
	
	public void activateEmployee(Long id) {
		Employee employee =
				employeeRepository.findById(id).orElseThrow(() -> new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND));
		
		if (employee.getStatus().equals(EmployeeType.ACTIVE)) {
			throw new IKPlusException(ErrorType.EMPLOYEE_ALREADY_ACTIVE);
		}
		
		employee.setStatus(EmployeeType.ACTIVE);
		employeeRepository.save(employee);
	}
	
	
	public void deactivateEmployee(Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isEmpty()) throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		Employee employee = optionalEmployee.get();
		if (employee.getStatus().equals(EmployeeType.PASSIVE))
			throw new IKPlusException(ErrorType.EMPLOYEE_ALREADY_PASSIVE);
		employee.setStatus(EmployeeType.PASSIVE);
		employeeRepository.save(employee);
	}
	
	public void delete(Long id) {
		Optional<Employee> byId = employeeRepository.findById(id);
		if (byId.isEmpty()) throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		employeeRepository.delete(byId.get());
	}
	
	public List<EmployeeResponse> findAll() {
		return employeeRepository.findAll().stream().map(EmployeeMapper.INSTANCE::toEmployeeResponse).toList();
	}
	
	public EmployeeResponse findById(Long id) {
		return employeeRepository.findById(id).map(EmployeeMapper.INSTANCE::toEmployeeResponse)
		                         .orElseThrow(() -> new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND));
	}
	
	public List<EmployeeResponse> findByCompanyId(Long companyId) {
		List<Employee> employees = employeeRepository.findByCompanyId(companyId);
		
		if (employees.isEmpty()) {
			throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		}
		
		return employees.stream().map(EmployeeMapper.INSTANCE::toEmployeeResponse).collect(Collectors.toList());
	}
	
	
	public List<EmployeeResponse> findByStatus(EmployeeType status) {
		List<Employee> employees = employeeRepository.findByStatus(status);
		
		if (employees.isEmpty()) {
			throw new IKPlusException(ErrorType.EMPLOYEE_NOT_FOUND);
		}
		
		return employees.stream().map(EmployeeMapper.INSTANCE::toEmployeeResponse).collect(Collectors.toList());
	}
}