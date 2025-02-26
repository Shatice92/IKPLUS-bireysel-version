package org.hatice.ikplus.service.usermanagement;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.usermanagement.AuthorizationRepository;
import org.hatice.ikplus.dto.request.userrequest.LoginRequestDto;
import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.request.userrequest.UserUpdateRequestDto;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.userresponse.LoginResponseDto;
import org.hatice.ikplus.dto.response.userresponse.UserResponse;
import org.hatice.ikplus.entity.usermanagement.Authorization;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.enums.UserStatus;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.UserMapper;
import org.hatice.ikplus.repository.usermanagement.UserRepository;
import org.hatice.ikplus.util.JwtManager;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final JwtManager jwtManager;
	private final RoleService roleService;
	private final AuthorizationRepository authorizationRepository;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;
	
	
	public void save(@Valid SaveUserRequestDto dto) {
		User user = userMapper.fromSaveUserDto(dto);
		userRepository.save(user);
	}
	
	public List<VwUser> getAllUsers() {
		return userRepository.getAllUsers();
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	
	public void register(@Valid RegisterRequestDto dto) {
		String encodedPassword = passwordEncoder.encode(dto.password());
		// RoleService'i parametre olarak geçiriyoruz
		User user = userMapper.fromRegisterDto(dto, roleService);
		user.setPassword(encodedPassword);
		userRepository.save(user);
		
		// Create authorization entry
		Authorization authorization = Authorization.builder()
		                                           .authId(user.getAuthId())
		                                           .userId(user.getId())
		                                           .createdAt(LocalDateTime.now())
		                                           .build();
		
		authorizationRepository.save(authorization);
		
		// Send verification email
		String verificationLink = "http://localhost:9090/v1/dev/user/verify/" + user.getAuthId();
		emailService.sendVerificationEmail(user.getEmail(), verificationLink);
	}
	
	public LoginResponseDto login(@Valid LoginRequestDto dto) {
		// 1. Adım: Kullanıcıyı email'e göre bul
		Optional<User> userOptional = userRepository.findByEmail(dto.email());
		
		if (userOptional.isEmpty()) {
			throw new IKPlusException(ErrorType.INVALID_CREDENTIALS);  // Kullanıcı bulunamadı
		}
		
		User user = userOptional.get();
		
		// 2. Adım: Şifreyi kontrol et (hash karşılaştırması)
		if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
			throw new IKPlusException(ErrorType.INVALID_CREDENTIALS);  // Şifre yanlış
		}
		
		// 3. Adım: Kullanıcının rolünü al
		Role role = roleService.findById(user.getRoleId())
		                       .orElseThrow(() -> new IKPlusException(ErrorType.ROLE_NOT_FOUND));
		
		// 4. Adım: JWT token oluştur
		String token = jwtManager.createToken(user.getAuthId(), role);
		
		LoginResponseDto responseDto = new LoginResponseDto();
		responseDto.setToken(token);
		responseDto.setUserId(user.getId());
		responseDto.setRole(role);
		
		return responseDto;
	}
	
	
	
	public UserResponse updateUserRole(Long id, RoleName newRoleName) {
		User user = userRepository.findById(id).orElseThrow(() -> new IKPlusException(ErrorType.USER_NOT_FOUND));
		
		Long roleId = roleService.findRoleIdByName(newRoleName);
		
		
		user.setRoleId(roleId);
		user.setUpdatedAt(LocalDateTime.now());
		userRepository.save(user);
		
		return new UserResponse(user.getStatus(), user.getRoleId(), user.getUpdatedAt());
	}
	
	
	public Optional<User> findByAuthId(UUID authId) {
		return userRepository.findByAuthId(authId);
	}
	
	public User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).orElseThrow(() -> new IKPlusException(ErrorType.USER_NOT_FOUND));
	}
	
	
	public Optional<TokenInfo> getUserProfileByToken(String token) {
		// Token'ı doğrula
		return jwtManager.validateToken(token);
		
	}
	
	
	public boolean updateUserStatus(UserStatus status, UUID authId) {
		
		// Kullanıcıyı UUID ile bul
		Optional<User> userOpt = userRepository.findByAuthId(authId);
		
		if (userOpt.isEmpty()) {
			return false;  // Kullanıcı bulunamadı
		}
		
		User user = userOpt.get();
		
		// Durum bilgisini güncelle
		user.setStatus(status);
		
		// Kullanıcıyı kaydet
		userRepository.save(user);
		
		return true;  // Durum başarıyla güncellendi
	}
	
	
	
	@Transactional
	public boolean updateUserProfile(User user) {
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			// Güncelleme sırasında hata oluşursa logla
			System.err.println("Profil güncellenirken hata oluştu: " + e.getMessage());
			return false;
		}
	}
}