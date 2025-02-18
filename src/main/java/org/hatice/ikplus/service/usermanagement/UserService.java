package org.hatice.ikplus.service.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.userrequest.LoginRequestDto;
import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.userresponse.LoginResponseDto;
import org.hatice.ikplus.dto.response.userresponse.UserResponse;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.UserMapper;
import org.hatice.ikplus.repository.usermanagement.UserRepository;
import org.hatice.ikplus.util.JwtManager;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.security.core.context.SecurityContextHolder;
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
		// RoleService'i parametre olarak geçiriyoruz
		User user = userMapper.fromRegisterDto(dto, roleService);
		userRepository.save(user);
	}
	
	public LoginResponseDto login(@Valid LoginRequestDto dto) {
		// 1. Adım: dto içindeki email ve password bilgisi ile kayıtlı bir kullanıcı var mı?
		Optional<User> userOptional = userRepository.findOptionalByEmailAndPassword(dto.email(), dto.password());
		
		// Kullanıcı bulunmazsa hata fırlatıyoruz
		if (userOptional.isEmpty()) {
			throw new IKPlusException(ErrorType.INVALID_CREDENTIALS);
		}
		
		// 2. Adım: Kullanıcıyı alıyoruz
		User user = userOptional.get();
		
		// 3. Adım: Kullanıcının rolünü alıyoruz
		Role role = roleService.findById(user.getRoleId())
		                       .orElseThrow(() -> new IKPlusException(ErrorType.ROLE_NOT_FOUND));  // Artık
		// roleId'yi kullanarak tek
		// bir rol alıyoruz
		
		// 4. Adım: JWT token oluşturuluyor
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
	
}