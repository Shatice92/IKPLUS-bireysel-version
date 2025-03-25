package org.hatice.ikplus.controller.companycontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.companyrequest.CompanyRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;

import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.service.companyservice.CompanyService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import static org.hatice.ikplus.constant.Endpoints.*;

@CrossOrigin("*")
@RestController
@RequestMapping(Endpoints.ADMIN_COMPANIES)
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyService companyService;
	private final JwtManager jwtManager;
	
	@PostMapping(Endpoints.SAVE)
	public ResponseEntity<BaseResponse<Boolean>> createCompany(@RequestBody CompanyRequestDto companyRequestDTO) {
		companyService.createCompany(companyRequestDTO);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Company created successfully")
		                                     .code(200).success(true).build());
	}
	
	@PutMapping(APPROVE)
	public ResponseEntity<BaseResponse<Boolean>> approveCompany(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		companyService.approveCompany(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Company approved successfully")
		                                     .code(200).success(true).build());
	}
	
	@PutMapping(REJECT)
	public ResponseEntity<BaseResponse<Boolean>> rejectCompany(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		companyService.rejectCompany(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Company rejected successfully")
		                                     .code(200).success(true).build());
	}
	
	
	@PutMapping(UPDATE)
	
	public ResponseEntity<BaseResponse<CompanyResponse>> updateCompany(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long id, @RequestBody CompanyRequestDto updatedCompany) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		CompanyResponse responseDTO = companyService.updateCompany(id, updatedCompany);
		return ResponseEntity.ok(BaseResponse.<CompanyResponse>builder().data(responseDTO)
		                                     .message("Company updated successfully").code(200).success(true).build());
	}
	
	
	@DeleteMapping(DELETE)
	
	public ResponseEntity<BaseResponse<Boolean>> deleteCompany(@PathVariable Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Company deleted successfully")
		                                     .code(200).success(true).build());
	}
	
	
	@GetMapping(GETBYID)
	
	public ResponseEntity<BaseResponse<CompanyResponse>> getCompanyById(@PathVariable Long id) {
		CompanyResponse responseDTO = companyService.getCompanyById(id);
		return ResponseEntity.ok(BaseResponse.<CompanyResponse>builder().data(responseDTO)
		                                     .message("Company retrieved successfully").code(200).success(true)
		                                     .build());
	}
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<CompanyResponse>>> getAllCompanies(@RequestHeader("Authorization") String authorizationHeader) {
		List<CompanyResponse> companies = companyService.getAllCompanies();
		return ResponseEntity.ok(BaseResponse.<List<CompanyResponse>>builder().data(companies)
		                                     .message("Companies retrieved successfully").code(200).success(true)
		                                     .build());
	}
	
	
}