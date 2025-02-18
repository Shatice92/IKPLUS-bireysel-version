package org.hatice.ikplus.controller.employeecontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.employeeresponse.EmployeeResponse;
import org.hatice.ikplus.entity.employeemanagement.Employee;
import org.hatice.ikplus.enums.EmployeeType;
import org.hatice.ikplus.service.employeeservice.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hatice.ikplus.constant.Endpoints.*;

@CrossOrigin("*")
@RestController
@RequestMapping(Endpoints.COMPANY_MANAGER_EMPLOYEES)
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
	public ResponseEntity<BaseResponse<EmployeeResponse>> updateEmployee(@PathVariable Long id,
	                                                                     @RequestBody UpdateEmployeeRequestDto dto) {
		
		return ResponseEntity.ok(
				BaseResponse.<EmployeeResponse>builder()
				            .data(employeeService.updateEmployee(id,dto))
				            .message("Employee updated successfully")
				            .code(200)
				            .success(true)
				            .build()
		);
	}
	
	
	
	@PutMapping(ACTIVATESTATUS)
	public ResponseEntity<BaseResponse<Boolean>> activateEmployee(@PathVariable Long id) {
		employeeService.activateEmployee(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee activated successfully")
		                                     .code(200).success(true).build());
	}
	
	@PutMapping(DEACTIVATESTATUS)
	public ResponseEntity<BaseResponse<Boolean>> deactivateEmployee(@PathVariable Long id) {
		employeeService.deactivateEmployee(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee deactivated " +
				                                                                            "successfully")
		                                     .code(200).success(true).build());
	}
	
	@DeleteMapping(DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteEmployee(@PathVariable Long id) {
		employeeService.delete(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee deleted successfully")
		                                     .code(200).success(true).build());
	}
	
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<EmployeeResponse>>> getEmployees() {
		return ResponseEntity.ok(BaseResponse.<List<EmployeeResponse>>builder()
		                                     .data(employeeService.getEmployeeListByRole())
		                                     .message("All employees listed")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	@GetMapping(GETBYID)
	public ResponseEntity<BaseResponse<EmployeeResponse>> getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok(BaseResponse.<EmployeeResponse>builder()
		                                     .data(employeeService.findById(id))
		                                     .message("Employee found successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	
	@GetMapping(GETBYCOMPANYID)
	public ResponseEntity<BaseResponse<List<EmployeeResponse>>> getEmployeesByCompany(@PathVariable Long companyId) {
		return ResponseEntity.ok(BaseResponse.<List<EmployeeResponse>>builder()
		                                     .data(employeeService.findByCompanyId(companyId))
		                                     .message("Employees of company ID " + companyId + " listed successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping(GETBYSTATUS)
	public ResponseEntity<BaseResponse<List<EmployeeResponse>>> getEmployeesByStatus(@RequestParam  EmployeeType status) {
		return ResponseEntity.ok(BaseResponse.<List<EmployeeResponse>>builder()
		                                     .data(employeeService.findByStatus(status))
		                                     .message("Employees with status " + status + " listed successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
}