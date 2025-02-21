package org.hatice.ikplus.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.usermanagement.RoleService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {
	
	private final @Lazy UserService userService;
	private final RoleService roleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		throw new UnsupportedOperationException("Bu servis sadece authId ile çalışır!");
	}
	
	public UserDetails loadUserById(UUID authId) {
		Optional<User> optionalUser = userService.findByAuthId(authId);
		if (optionalUser.isEmpty()) {
			log.warn("Kullanıcı bulunamadı! UserId: " + authId);
			return null;
		}
		
		User user = optionalUser.get();
		
		Optional<Role> optionalRole = roleService.findById(user.getRoleId());
		if (optionalRole.isEmpty()) {
			log.warn("Kullanıcıya atanmış bir rol bulunamadı! UserID: " + user.getId());
			return null;
		}
		
		Role role = optionalRole.get();
		String roleName = role.getName() != null ? role.getName().name() : "VISITOR";
		
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + roleName);
		
		return org.springframework.security.core.userdetails.User.builder()
		                                                         .username(user.getEmail())
		                                                         .password(user.getPassword())
		                                                         .accountExpired(false)
		                                                         .accountLocked(false)
		                                                         .credentialsExpired(false)
		                                                         .disabled(false)
		                                                         .authorities(Collections.singletonList(grantedAuthority))
		                                                         .build();
	}
}