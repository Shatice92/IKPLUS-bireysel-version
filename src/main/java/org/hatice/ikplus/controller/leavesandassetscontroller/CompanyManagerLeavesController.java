package org.hatice.ikplus.controller.leavesandassetscontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.leavesandassetrequest.AddLeaveRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.CreateAssetRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.LeaveSaveRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateLeaveRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.Leaves;
import org.hatice.ikplus.mapper.LeaveMapper;
import org.hatice.ikplus.service.leavesandassetsservice.LeavesService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hatice.ikplus.constant.Endpoints.*;


@CrossOrigin("*")
@RestController
@RequestMapping(COMPANY_MANAGER_LEAVES)
@RequiredArgsConstructor
public class CompanyManagerLeavesController {
	private final LeavesService leaveService;
	private final JwtManager jwtManager;
	
	
	@PutMapping(APPROVE)
	public ResponseEntity<BaseResponse<Boolean>> approveLeave(@PathVariable Long id) {
		leaveService.approveLeave(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Leave approved successfully")
		                                     .code(200).success(true).build());
	}
	
	@PutMapping(REJECT)
	public ResponseEntity<BaseResponse<Boolean>> rejectLeave(@PathVariable Long id) {
		leaveService.rejectLeave(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Leave rejected successfully")
		                                     .code(200).success(true).build());
	}
	
	
	@GetMapping(FINDBYID)
	public ResponseEntity<BaseResponse<LeaveResponse>> getLeaveById(@PathVariable Long id) {
		LeaveResponse response = leaveService.getLeaveById(id);
		return ResponseEntity.ok(BaseResponse.<LeaveResponse>builder().data(response)
		                                     .message("Leave found successfully").code(200).success(true).build());
	}
	
	@GetMapping(GETBYEMPLOYEEID)
	public ResponseEntity<BaseResponse<List<LeaveResponse>>> getLeavesByEmployeeId(@RequestHeader(
			"Authorization") String authorizationHeader,@PathVariable Long employeeId) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		List<LeaveResponse> leaves = leaveService.getLeavesByEmployeeId(employeeId);
		return ResponseEntity.ok(BaseResponse.<List<LeaveResponse>>builder().data(leaves)
		                                     .message("Leaves of employee listed successfully").code(200).success(true)
		                                     .build());
	}
	
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Leaves>> createAsset(@RequestHeader("Authorization") String authorizationHeader
			, @RequestBody @Valid AddLeaveRequestDto dto) {
		// Authorization header'ından token'ı al
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		
		
		return ResponseEntity.ok(BaseResponse.<Leaves>builder().data(leaveService.save(dto))
		                                     .message("Leave request created successfully").code(200).success(true)
		                                     .build());
	}
	
}