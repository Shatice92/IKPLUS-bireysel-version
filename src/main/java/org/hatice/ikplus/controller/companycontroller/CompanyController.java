package org.hatice.ikplus.controller.companycontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.companyrequest.CompanyRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;

import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.service.companyservice.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}