package org.hatice.ikplus.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	private final JwtManager jwtManager;
	private final JwtUserDetails jwtUserDetails;
	
	@Autowired
	public JwtTokenFilter(JwtManager jwtManager, @Lazy JwtUserDetails jwtUserDetails) {
		this.jwtManager = jwtManager;
		this.jwtUserDetails = jwtUserDetails;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("JwtTokenFilter doFilterInternal çalıştı...");
		
		
		String requestURI = request.getRequestURI();
		
		
		List<String> openEndpoints = Arrays.asList(
				"/v1/dev/user/login",
				"/v1/dev/user/register",
				"/v1/dev/password/request",
				"/v1/dev/password/reset", "/reset-password.html"  // Statik sayfaya erişimi de serbest bırak
		);
		
		
		if (openEndpoints.contains(requestURI)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		String authorizationHeader = request.getHeader("Authorization");
		log.debug("Gelen Authorization Header: {}", authorizationHeader);
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			log.debug("Extracted Token: {}", token);
			
			Optional<TokenInfo> tokenInfoOptional = jwtManager.validateToken(token);
			if (tokenInfoOptional.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Geçersiz token");
				return;
			}
			
			TokenInfo tokenInfo = tokenInfoOptional.get();
			UUID authId = tokenInfo.getAuthId();
			RoleName role = tokenInfo.getRole();
			log.debug("Tokenden alınan authId: {}, Rol: {}", authId, role);
			
			UserDetails userDetails = jwtUserDetails.loadUserById(authId);
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
			
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		} else {
			log.warn("Authorization header'ı 'Bearer ' ile başlamıyor.");
		}
		
		filterChain.doFilter(request, response);
	}
}