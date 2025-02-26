package org.hatice.ikplus.controller.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.repository.usermanagement.AuthorizationRepository;
import org.hatice.ikplus.dto.request.userrequest.LoginRequestDto;
import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;

import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.userrequest.*;

import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.userresponse.LoginResponseDto;
import org.hatice.ikplus.dto.response.userresponse.UserProfileResponse;
import org.hatice.ikplus.dto.response.userresponse.UserResponse;
import org.hatice.ikplus.entity.usermanagement.Authorization;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.enums.UserStatus;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.service.usermanagement.RoleService;
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
	private final AuthorizationRepository authorizationRepository;
	private final org.hatice.ikplus.repository.usermanagement.UserRepository userRepository;
	private final RoleService roleService;
	
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
	
	
	@PutMapping(UPDATE_STATUS)
	public ResponseEntity<BaseResponse<Boolean>> updateUserStatus(
			@RequestBody UserStatusRequestDto request,
			@RequestHeader("Authorization") String token) {
		
		// Token'ı alıp, kullanıcı bilgilerini doğruluyoruz
		String userToken = token.replace("Bearer ", "");
		Optional<TokenInfo> tokenInfoOpt = userService.getUserProfileByToken(userToken);
		
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
			                     .body(BaseResponse.<Boolean>builder()
			                                       .code(403)
			                                       .data(false)
			                                       .message("Geçersiz token!")
			                                       .success(false)
			                                       .build());
		}
		
		TokenInfo tokenInfo = tokenInfoOpt.get();
		
		// Kullanıcıyı token üzerinden buluyoruz
		Optional<User> userOpt = userService.findByAuthId(tokenInfo.getAuthId());
		
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			                     .body(BaseResponse.<Boolean>builder()
			                                       .code(404)
			                                       .data(false)
			                                       .message("Kullanıcı bulunamadı!")
			                                       .success(false)
			                                       .build());
		}
		
		User user = userOpt.get();
		
		// Kullanıcı durumunu güncellerken sadece geçerli kullanıcıyı hedefliyoruz
		boolean isUpdated = userService.updateUserStatus(request.status(), user.getAuthId());
		
		if (isUpdated) {
			return ResponseEntity.ok(BaseResponse.<Boolean>builder()
			                                     .code(200)
			                                     .data(true)
			                                     .message("Durum başarıyla güncellendi")
			                                     .success(true)
			                                     .build());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			                     .body(BaseResponse.<Boolean>builder()
			                                       .code(400)
			                                       .data(false)
			                                       .message("Durum güncellenirken bir hata oluştu. Kullanıcı bulunamadı veya işlem başarısız oldu.")
			                                       .success(false)
			                                       .build());
		}
	}
	
	
	
	@PutMapping(UPDATE_USER_PROFILE)
	public ResponseEntity<BaseResponse<Boolean>> updateUserProfile(
			@RequestBody UserUpdateRequestDto userDTO,
			@RequestHeader("Authorization") String token) {
		
		// Token ile kullanıcı bilgilerini al
		String userToken = token.replace("Bearer ", "");
		Optional<TokenInfo> tokenInfoOpt = userService.getUserProfileByToken(userToken);
		
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
			                     .body(BaseResponse.<Boolean>builder()
			                                       .code(403)
			                                       .data(false)
			                                       .message("Geçersiz token!")
			                                       .success(false)
			                                       .build());
		}
		
		TokenInfo tokenInfo = tokenInfoOpt.get();
		Optional<User> userOpt = userService.findByAuthId(tokenInfo.getAuthId());
		
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			                     .body(BaseResponse.<Boolean>builder()
			                                       .code(404)
			                                       .data(false)
			                                       .message("Kullanıcı bulunamadı!")
			                                       .success(false)
			                                       .build());
		}
		
		User user = userOpt.get();
		user.setGender(userDTO.gender() != null ? userDTO.gender() : user.getGender());
		user.setPhoneNumber(userDTO.phoneNumber() != null ? userDTO.phoneNumber() : user.getPhoneNumber());
		user.setBirthDate(userDTO.birthDate() != null ? userDTO.birthDate() : user.getBirthDate());
		user.setMaritalStatus(userDTO.maritalStatus() != null ? userDTO.maritalStatus() : user.getMaritalStatus());
		user.setBloodType(userDTO.bloodType() != null ? userDTO.bloodType() : user.getBloodType());
		user.setIdentificationNumber(userDTO.identificationNumber() != null ? userDTO.identificationNumber() : user.getIdentificationNumber());
		user.setNationality(userDTO.nationality() != null ? userDTO.nationality() : user.getNationality());
		user.setEducationLevel(userDTO.educationLevel() != null ? userDTO.educationLevel() : user.getEducationLevel());
		user.setStatus(userDTO.status() != null ? userDTO.status() : user.getStatus());
		
		// Kullanıcıyı güncelleme işlemi
		boolean isUpdated = userService.updateUserProfile(user);
		
		if (isUpdated) {
			return ResponseEntity.ok(BaseResponse.<Boolean>builder()
			                                     .code(200)
			                                     .data(true)
			                                     .message("Profil başarıyla güncellendi.")
			                                     .success(true)
			                                     .build());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			                     .body(BaseResponse.<Boolean>builder()
			                                       .code(500)
			                                       .data(false)
			                                       .message("Profil güncellenirken bir hata oluştu.")
			                                       .success(false)
			                                       .build());
		}
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
	@GetMapping(DASHBOARD)
	public ResponseEntity<BaseResponse<RoleName>> getUserRole(@RequestHeader("Authorization") String authorizationHeader) {
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		User user = userOpt.get();
		
		Optional<Role> byId = roleService.findById(user.getRoleId());
		RoleName role = byId.get().getName();
		
		return ResponseEntity.ok(BaseResponse.<RoleName>builder().code(200).data(role)
		                                     .message("Kullanıcı rolü başarıyla alındı").success(true).build());
	}
	
	
	
}