package org.hatice.ikplus.controller.usermanagement;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.leavesandassetrequest.AddLeaveTypeRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveTypeResponse;
import org.hatice.ikplus.dto.response.userresponse.UserResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.service.leavesandassetsservice.LeaveTypesService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
	private final UserService userService;
	private final JwtManager jwtManager;
	private final LeaveTypesService leaveTypesService;
	
	@PostMapping(SAVE_USER)
	public ResponseEntity<BaseResponse<Boolean>> save(@RequestBody @Valid SaveUserRequestDto dto) {
		userService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true)
		                                     .message("User Başarıyla Kaydedildi.").success(true).build());
	}
	
	@GetMapping(GET_ALL_USERS)
	public ResponseEntity<BaseResponse<List<VwUser>>> getAllUsers() {
		return ResponseEntity.ok(BaseResponse.<List<VwUser>>builder().code(200).data(userService.getAllUsers())
		                                     .message("Kullanıcılar Başarıyla Getirildi.").success(true).build());
	}
	
	@GetMapping(FIND_BY_ID)
	public ResponseEntity<BaseResponse<User>> findById(@PathVariable Long id) {
		User user = userService.findById(id).orElseThrow(() -> new IKPlusException(ErrorType.USER_NOT_FOUND));
		return ResponseEntity.ok(BaseResponse.<User>builder().code(200).data(user)
		                                     .message("Kullanıcı Başarıyla Bulundu.").success(true).build());
	}
	
	
	@PutMapping(UPDATE_USER)
	public ResponseEntity<BaseResponse<UserResponse>> updateUserRole(@PathVariable Long id,
	                                                                 @RequestParam RoleName newRoleName) {
		UserResponse userResponse = userService.updateUserRole(id, newRoleName);
		// Başarılı yanıt döndürüyoruz
		return ResponseEntity.ok(BaseResponse.<UserResponse>builder().code(200)
		                                     .message("User role successfully updated").data(userResponse).success(true)
		                                     .build());
	}
	
	
	@PostMapping(ADD_LEAVE_TYPE)
	public ResponseEntity<BaseResponse<LeaveTypes>> saveLeave(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody AddLeaveTypeRequestDto dto) {  // @RequestBody ekledim
		
		String token = authorizationHeader.replace("Bearer ", "");
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
		// Enum dönüşümü
		TypeLeaves leaveType;
		try {
			leaveType = TypeLeaves.valueOf(dto.getLeavesName());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			                     .body(BaseResponse.<LeaveTypes>builder()
			                                       .message("Geçersiz izin türü!")
			                                       .success(false)
			                                       .code(400)
			                                       .build());
		}
		
		LeaveTypes newLeave = leaveTypesService.save(new LeaveTypes(leaveType));
		return ResponseEntity.ok(BaseResponse.<LeaveTypes>builder()
		                                     .message("İzin türü başarıyla eklendi.")
		                                     .data(newLeave)
		                                     .success(true)
		                                     .code(200)
		                                     .build());
	}
	
	
	
	@GetMapping(LIST_LEAVE_TYPES)
	public ResponseEntity<BaseResponse<List<LeaveTypeResponse>>> getAllLeaveTypes(@RequestHeader("Authorization") String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "");
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		return ResponseEntity.ok(BaseResponse.<List<LeaveTypeResponse>>builder().message("All leavestypes retrieved .")
		                                     .data(leaveTypesService.getAllLeaveTypes()).success(true).code(200)
		                                     .build());
	}
	
}