package org.hatice.ikplus.controller.employeecontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.service.employeeservice.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hatice.ikplus.constant.Endpoints.*;


@RestController
@RequestMapping(Endpoints.EMPLOYEE)
@RequiredArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;
	
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Boolean>> addEmployee(@RequestBody AddEmployeeRequestDto dto) {
		employeeService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee saved successfully")
		                                     .code(200).success(true).build());
	}
	
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<Employee>> updateEmployee(
			@PathVariable Long id,
			@RequestBody UpdateEmployeeRequestDto dto) {
		
		dto.setUserId(id);
		
		Employee updatedEmployee = employeeService.updateEmployee(dto);
		return ResponseEntity.ok(BaseResponse.<Employee>builder()
		                                     .data(updatedEmployee)
		                                     .message("Employee updated successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	
	@PutMapping(ACTIVATESTATUS)
	public ResponseEntity<BaseResponse<Boolean>> activateEmployee(@PathVariable Long id){
		employeeService.activateEmployee(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee activated successfully")
		                                     .code(200).success(true).build());
	}
	
	@PutMapping(DEACTIVATESTATUS)
	public ResponseEntity<BaseResponse<Boolean>> deactivateEmployee(@PathVariable Long id){
		employeeService.deactivateEmployee(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee deactivated successfully")
		                                     .code(200).success(true).build());
	}
	
	@DeleteMapping(DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteEmployee(@PathVariable Long id){
		employeeService.delete(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee deleted successfully")
		                                     .code(200).success(true).build());
	}
	
}