package org.hatice.ikplus.controller.companycontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.companyrequest.CompanyRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;

import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.service.companyservice.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.hatice.ikplus.constant.Endpoints.*;

@CrossOrigin("*")
@RestController
@RequestMapping(Endpoints.ADMIN_COMPANIES)
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyService companyService;
	
	@PostMapping(Endpoints.SAVE)
	public ResponseEntity<BaseResponse<Boolean>> createCompany(@RequestBody CompanyRequestDto companyRequestDTO) {
		 companyService.createCompany(companyRequestDTO);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Company created successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}

	@PutMapping(APPROVE)

	public ResponseEntity<BaseResponse<CompanyResponse>> approveCompany(@PathVariable Long id) {
		CompanyResponse responseDTO = companyService.approveCompany(id);
		return ResponseEntity.ok(BaseResponse.<CompanyResponse>builder()
		                                     .data(responseDTO)
		                                     .message("Company approved successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PutMapping(REJECT)

	public ResponseEntity<BaseResponse<CompanyResponse>> rejectCompany(@PathVariable Long id) {
		CompanyResponse responseDTO = companyService.rejectCompany(id);
		return ResponseEntity.ok(BaseResponse.<CompanyResponse>builder()
		                                     .data(responseDTO)
		                                     .message("Company rejected successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	

	@PutMapping(UPDATE)

	public ResponseEntity<BaseResponse<CompanyResponse>> updateCompany(@PathVariable Long id,
	                                                                   @RequestBody CompanyRequestDto updatedCompany) {
		CompanyResponse responseDTO = companyService.updateCompany(id, updatedCompany);
		return ResponseEntity.ok(BaseResponse.<CompanyResponse>builder()
		                                     .data(responseDTO)
		                                     .message("Company updated successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	

	@DeleteMapping(DELETE)

	public ResponseEntity<BaseResponse<Boolean>> deleteCompany(@PathVariable Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Company deleted successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	

	@GetMapping(GETBYID)

	public ResponseEntity<BaseResponse<CompanyResponse>> getCompanyById(@PathVariable Long id) {
		CompanyResponse responseDTO = companyService.getCompanyById(id);
		return ResponseEntity.ok(BaseResponse.<CompanyResponse>builder()
		                                     .data(responseDTO)
		                                     .message("Company retrieved successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping(LIST)

	public ResponseEntity<BaseResponse<List<CompanyResponse>>> getAllCompanies() {
		List<CompanyResponse> companies = companyService.getAllCompanies();
		return ResponseEntity.ok(BaseResponse.<List<CompanyResponse>>builder()
		                                     .data(companies)
		                                     .message("Companies retrieved successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
}