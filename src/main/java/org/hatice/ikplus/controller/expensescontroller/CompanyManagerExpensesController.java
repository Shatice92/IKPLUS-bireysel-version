package org.hatice.ikplus.controller.expensescontroller;


import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.employeeresponse.EmployeeResponse;
import org.hatice.ikplus.entity.companymanagement.Company;
import org.hatice.ikplus.entity.expensemanagement.Expenses;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.companyservice.CompanyService;
import org.hatice.ikplus.service.expensesservice.ExpensesService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hatice.ikplus.constant.Endpoints.GETBYCOMPANYID;
import static org.hatice.ikplus.constant.Endpoints.LIST;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.COMPANY_MANAGER_EXPENSES)
public class CompanyManagerExpensesController {
	private final UserService userService;
	private final CompanyService companyService;
	private final ExpensesService expensesService;
	private final JwtManager jwtManager;
	
	@GetMapping(GETBYCOMPANYID)
	public ResponseEntity<BaseResponse<List<Expenses>>> getExpensesByCompany(@RequestHeader("Authorization") String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		TokenInfo tokenInfo = tokenInfoOpt.get();
		UUID authId = tokenInfo.getAuthId();
		
		// authId ile kullanıcıyı bul
		Optional<User> userOpt = userService.findByAuthId(authId);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Kullanıcı bulunamadı
		}
		
		
		User user = userOpt.get();
		
		Company company = companyService.getCompanyByCompanyManagerId(user.getCompanyManagerId());
		
		Long companyId = company.getId();
		
		List<Expenses> expenses = expensesService.findExpensesByCompanyId(companyId);
		return ResponseEntity.ok(BaseResponse.<List<Expenses>>builder().data(expenses)
		                                     .message("Expenses of company ID " + companyId + " listed successfully.")
		                                     .code(200).success(true).build());
		
	}
}