package org.hatice.ikplus.controller.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.usermanagement.AuthorizationRepository;
import org.hatice.ikplus.dto.request.userrequest.LoginRequestDto;
import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.userresponse.LoginResponseDto;
import org.hatice.ikplus.dto.response.userresponse.UserResponse;
import org.hatice.ikplus.entity.usermanagement.Authorization;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.enums.UserStatus;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
	private final UserService userService;
	private final AuthorizationRepository authorizationRepository;
	private final org.hatice.ikplus.repository.usermanagement.UserRepository userRepository;
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> registerUser(@RequestBody @Valid RegisterRequestDto dto) {
		if (!dto.password().equals(dto.rePassword())) throw new IKPlusException(ErrorType.PASSWORD_MISMATCH);
		userService.register(dto);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true)
		                                     .message("Mailinize Onay Kodu Gönderildi.").success(true).build());
	}
	
	@PostMapping(LOGIN)
	public ResponseEntity<BaseResponse<LoginResponseDto>> login(@RequestBody @Valid LoginRequestDto dto) {
		// UserService'ten login fonksiyonunu çağırıyoruz
		LoginResponseDto loginResponse = userService.login(dto);
		
		// Geriye token ve kullanıcı bilgilerini içeren bir response dönüyoruz
		return ResponseEntity.ok(BaseResponse.<LoginResponseDto>builder().code(200)
		                                     .data(loginResponse)  // Token ve kullanıcı bilgileri burada olacak
		                                     .message("Giriş başarılı.").success(true).build());
	}
	
	
	@PostMapping(ADMIN_SAVE_USER)
	public ResponseEntity<BaseResponse<Boolean>> save(@RequestBody @Valid SaveUserRequestDto dto) {
		userService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true)
		                                     .message("User Başarıyla Kaydedildi.").success(true).build());
	}
	
	@GetMapping(ADMIN_GET_ALL_USERS)
	public ResponseEntity<BaseResponse<List<VwUser>>> getAllUsers() {
		return ResponseEntity.ok(BaseResponse.<List<VwUser>>builder().code(200).data(userService.getAllUsers())
		                                     .message("Kullanıcılar Başarıyla Getirildi.").success(true).build());
	}
	
	@GetMapping(ADMIN_FIND_BY_ID )
	public ResponseEntity<BaseResponse<User>> findById(@PathVariable Long id) {
		User user = userService.findById(id).orElseThrow(() -> new IKPlusException(ErrorType.USER_NOT_FOUND));
		return ResponseEntity.ok(BaseResponse.<User>builder().code(200).data(user)
		                                     .message("Kullanıcı Başarıyla Bulundu.").success(true).build());
	}
	
	
	@PutMapping(ADMIN_UPDATE_USER)
	public ResponseEntity<BaseResponse<UserResponse>> updateUserRole(@PathVariable Long id,
	                                                                 @RequestParam RoleName newRoleName) {
		UserResponse userResponse = userService.updateUserRole(id, newRoleName);
		// Başarılı yanıt döndürüyoruz
		return ResponseEntity.ok(BaseResponse.<UserResponse>builder().code(200)
		                                     .message("User role successfully updated").data(userResponse).success(true)
		                                     .build());
	}
	
	
	public ResponseEntity<BaseResponse<User>> findUserByAuthId(@PathVariable UUID id) {
		Optional<User> optionalUser = userService.findByAuthId(id);
		if (optionalUser.isEmpty()) throw new IKPlusException(ErrorType.USER_NOT_FOUND);
		User user = optionalUser.get();
		
		return ResponseEntity.ok(BaseResponse.<User>builder().code(200).data(user)
		                                     .message("User found successfully").success(true).build());
	}
	
	@GetMapping(VERIFY)
	public ResponseEntity<?> verifyUser(@PathVariable UUID authId) {
		Authorization authorization = authorizationRepository.findByAuthId(authId)
		                                                     .orElseThrow(() -> new IKPlusException(ErrorType.AUTHORIZATION_NOT_FOUND));
		
		User user = userRepository.findById(authorization.getUserId())
		                          .orElseThrow(() -> new IKPlusException(ErrorType.USER_NOT_FOUND));
		
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);
		
		// ✅ Başarılı doğrulama sonrası basit bir HTML sayfası dönelim
		String successHtml = "<html><body><h1>Email Dogrulandi ✅</h1><p>Artik hesabiniza giris yapabilirsiniz" +
				".</p></body></html>";
		
		return ResponseEntity.ok()
		                     .header("Content-Type", "text/html")
		                     .body(successHtml);
	}
	
	
	
}