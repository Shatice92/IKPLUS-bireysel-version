package org.hatice.ikplus.service.usermanagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.mapper.usermapper.UserMapper;
import org.hatice.ikplus.repository.usermanagement.UserRepository;
import org.hatice.ikplus.view.userview.VwUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public void save(@Valid SaveUserRequestDto dto) {
		User user = userMapper.fromSaveUserDto(dto);
		userRepository.save(user);
	}
	
	public List<VwUser> getAllUsers() {
		return userRepository.getAllUsers();
	}
	
	public User findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		return userOptional.orElse(null);
	}
}