package org.hatice.ikplus.controller.usermanagement;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.userresponse.UserResponse;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
	private final UserService userService;
	
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
	
	@GetMapping(FIND_BY_ID )
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
}