package org.hatice.ikplus.controller.employeecontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.employeerequest.AddEmployeeDocumentRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeDocumentRequestDto;
import org.hatice.ikplus.dto.request.employeerequest.UpdateEmployeeRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.employeeresponse.EmployeeDocumentResponseDto;
import org.hatice.ikplus.dto.response.employeeresponse.EmployeeResponse;
import org.hatice.ikplus.service.employeeservice.EmployeeDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.hatice.ikplus.constant.Endpoints.DELETE;
import static org.hatice.ikplus.constant.Endpoints.UPDATE;


@RestController
@RequestMapping(Endpoints.COMPANY_MANAGER_EMPLOYEE_DOCUMENT)
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeDocumentController {
	private final EmployeeDocumentService employeeDocumentService;
	
	
	@PostMapping(Endpoints.SAVE)
	public ResponseEntity<BaseResponse<Boolean>> saveEmployeeDocument(@RequestBody AddEmployeeDocumentRequestDto dto) {
		employeeDocumentService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee Document saved successfully")
		                                     .code(200).success(true).build());
	}
	
	
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<EmployeeDocumentResponseDto>> updateEmployee(@PathVariable Long id,
	                                                                                @RequestBody UpdateEmployeeDocumentRequestDto dto) {
		
		return ResponseEntity.ok(
				BaseResponse.<EmployeeDocumentResponseDto>builder()
				            .data(employeeDocumentService.updateEmployeeDocument(id,dto))
				            .message("Employee Document updated successfully")
				            .code(200)
				            .success(true)
				            .build()
		);
	}
	
	
	@DeleteMapping(DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteEmployeeDocument(@PathVariable Long id) {
		employeeDocumentService.delete(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Employee Document deleted " +
				                                                                            "successfully")
		                                     .code(200).success(true).build());
	}
}