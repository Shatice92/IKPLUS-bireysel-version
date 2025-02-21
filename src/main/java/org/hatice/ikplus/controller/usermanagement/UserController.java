package org.hatice.ikplus.controller.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.userrequest.LoginRequestDto;
import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.request.userrequest.UserStatusRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.userresponse.LoginResponseDto;
import org.hatice.ikplus.dto.response.userresponse.UserProfileResponse;
import org.hatice.ikplus.dto.response.userresponse.UserResponse;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.HttpStatus;
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
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> registerUser(@RequestBody @Valid RegisterRequestDto dto) {
		if (!dto.password().equals(dto.rePassword())) throw new IKPlusException(ErrorType.PASSWORD_MISMATCH);
		userService.register(dto);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().code(200).data(true)
		                                     .message("Üyelik Başarıyla Oluşturuldu.").success(true).build());
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
	
	
	public ResponseEntity<BaseResponse<User>> findUserByAuthId(@PathVariable UUID id) {
		Optional<User> optionalUser = userService.findByAuthId(id);
		if (optionalUser.isEmpty()) throw new IKPlusException(ErrorType.USER_NOT_FOUND);
		User user = optionalUser.get();
		
		return ResponseEntity.ok(BaseResponse.<User>builder().code(200).data(user).message("User found successfully")
		                                     .success(true).build());
	}
	
	// Kullanıcı bilgilerini token ile al
	@GetMapping(GET_PROFILE_BY_TOKEN)
	public ResponseEntity<BaseResponse<UserProfileResponse>> getProfileByToken(@RequestHeader("Authorization") String authorizationHeader) {
		// Authorization header'ından token'ı al
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token ile kullanıcı bilgilerini al
		Optional<TokenInfo> tokenInfoOpt = userService.getUserProfileByToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		
		TokenInfo tokenInfo = tokenInfoOpt.get();
		Optional<User> userOpt = userService.findByAuthId(tokenInfo.getAuthId());
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Kullanıcı bulunamadı
		}
		
		User user = userOpt.get();
		UserProfileResponse userProfileResponse =
				new UserProfileResponse(user.getFirstName(), user.getLastName(), user.getEmail(), user.getGender(),
				                        user.getPhoneNumber(), user.getBirthDate(), user.getMaritalStatus(),
				                        user.getBloodType(), user.getIdentificationNumber(), user.getNationality(),
				                        user.getEducationLevel(), user.getStatus());
		
		return ResponseEntity.ok(BaseResponse.<UserProfileResponse>builder().code(200).data(userProfileResponse)
		                                     .message("User found successfully").success(true).build());
	}
	
	
	// Kullanıcı durumunu güncelle
	@PutMapping(UPDATE_STATUS)
	public ResponseEntity<BaseResponse<String>> updateUserStatus(@RequestBody UserStatusRequestDto request,
	                                                             @RequestHeader("Authorization") String token) {
		try {
			
			boolean isUpdated = userService.updateUserStatus(String.valueOf(request.status()), token);
			
			if (isUpdated) {
				// Durum güncelleme başarılı ise
				BaseResponse<String> response =
						BaseResponse.<String>builder().code(200).data("Status updated successfully")
						            .message("User status updated successfully.").success(true).build();
				return ResponseEntity.ok(response);
			}
			else {
				// Kullanıcı bulunamadı
				BaseResponse<String> response = BaseResponse.<String>builder().code(400).data("Failed to update " +
						                                                                              "status")
				                                            .message("User not found or failed to update.")
				                                            .success(false).build();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}
		catch (Exception e) {
			// Hata durumunda response
			BaseResponse<String> response =
					BaseResponse.<String>builder().code(500).data("Error occurred during status update")
					            .message("An unexpected error occurred while updating the status.").success(false)
					            .build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}