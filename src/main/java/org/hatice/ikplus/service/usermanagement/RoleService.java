package org.hatice.ikplus.service.usermanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.userrequest.SaveRoleRequestDto;
import org.hatice.ikplus.dto.request.userrequest.UpdateRoleRequestDto;
import org.hatice.ikplus.dto.response.userresponse.RoleResponse;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.repository.usermanagement.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository roleRepository;
	
	public void save(SaveRoleRequestDto dto) {
		Role newRole = Role.builder().name(dto.name()).permissions(dto.permissions()).build();
		
		roleRepository.save(newRole);
	}
	
	
	public List<RoleResponse> getAllRoles() {
		List<Role> roles = roleRepository.findAll();
		
		
		return roles.stream().map(role -> new RoleResponse(role.getName(), role.getPermissions()))
		            .collect(Collectors.toList());
	}
	
	public RoleResponse updateRole(Long roleId, UpdateRoleRequestDto requestDto) {
		Optional<Role> roleOptional = roleRepository.findById(roleId);
		
		if (roleOptional.isEmpty()) {
			return null;
		}
		
		Role role = roleOptional.get();
		role.setPermissions(requestDto.permissions());
		
		roleRepository.save(role);
		
		
		return new RoleResponse(role.getName(), role.getPermissions());
	}
	
	public Role findById(Long roleId) {
		Optional<Role> roleOptional = roleRepository.findById(roleId);
		if (roleOptional.isEmpty()) {
			return null;
		}
		return roleOptional.get();
	}
	
	public Long findRoleIdByName(RoleName roleName) {
		// RoleName'e göre Role'ü alıyoruz
		Role role = roleRepository.findByName(roleName)
		                          .orElseThrow(() -> new IKPlusException(ErrorType.ROLE_NOT_FOUND)); // Exception'ı doğru kullanıyoruz
		return role.getId(); // Role ID'sini döndürüyoruz
	}
	
	
	
	
	
}