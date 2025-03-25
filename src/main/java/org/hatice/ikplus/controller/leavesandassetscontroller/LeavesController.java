package org.hatice.ikplus.controller.leavesandassetscontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.leavesandassetrequest.AddLeaveRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateLeaveRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.Leaves;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.LeaveMapper;
import org.hatice.ikplus.service.leavesandassetsservice.LeaveTypesService;
import org.hatice.ikplus.service.leavesandassetsservice.LeavesService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hatice.ikplus.constant.Endpoints.*;

@CrossOrigin("*")
@RestController
@RequestMapping(EMPLOYEE_LEAVES)
@RequiredArgsConstructor
public class LeavesController {
	private final LeavesService leaveService;
	private final JwtManager jwtManager;
	private final UserService userService;
	private final LeaveTypesService leaveTypesService;
	
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Leaves>> saveLeave(@RequestHeader("Authorization") String authorizationHeader,
	                                                      @RequestBody @Valid AddLeaveRequestDto dto) {
		
		// Authorization header'ından token'ı al
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		TokenInfo tokenInfo = tokenInfoOpt.get();
		UUID authId = tokenInfo.getAuthId();
		Optional<User> byAuthId = userService.findByAuthId(authId);
		if (!byAuthId.isPresent()) {
			throw new IKPlusException(ErrorType.USER_NOT_FOUND);
		}
		User user = byAuthId.get();
		dto.setEmployeeId(user.getEmployeeId());
		
		
		return ResponseEntity.ok(BaseResponse.<Leaves>builder().data(leaveService.save(dto))
		                                     .message("Leave request created successfully").code(200).success(true)
		                                     .build());
	}
	
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<LeaveResponse>> updateLeave(@PathVariable Long id,
	                                                               @RequestBody UpdateLeaveRequestDto dto) {
		LeaveResponse response = leaveService.updateLeave(id, dto);
		return ResponseEntity.ok(BaseResponse.<LeaveResponse>builder().data(response)
		                                     .message("Leave updated successfully").code(200).success(true).build());
	}
	
	
	@GetMapping(GETLEAVEBYEMPLOYEEID)
	public ResponseEntity<BaseResponse<List<LeaveResponse>>> getLeavesByEmployeeId(@RequestHeader("Authorization") String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
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
		Long employeeId = user.getEmployeeId();
		user.setCompanyManagerId(user.getCompanyManagerId());
		List<LeaveResponse> leaves = leaveService.getLeavesByEmployeeId(employeeId);
		
		return ResponseEntity.ok(BaseResponse.<List<LeaveResponse>>builder().data(leaves)
		                                     .message("Leaves of employee listed successfully").code(200).success(true)
		                                     .build());
	}
	
	
	
	
	
	
}