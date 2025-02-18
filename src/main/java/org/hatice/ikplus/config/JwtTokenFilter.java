package org.hatice.ikplus.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.enums.RoleName;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component  // Eğer @Service değilse @Component olmalı
public class JwtTokenFilter extends OncePerRequestFilter {
	
	private final JwtManager jwtManager;
	private final JwtUserDetails jwtUserDetails;
	
	@Autowired
	public JwtTokenFilter(JwtManager jwtManager, JwtUserDetails jwtUserDetails) {
		this.jwtManager = jwtManager;
		this.jwtUserDetails = jwtUserDetails;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("JwtTokenFilter doFilterInternal çalıştı...");
		
		// Eğer istek login endpoint'ine geliyorsa, token kontrolü yapma
		if (request.getRequestURI().equals("/v1/dev/user/login")) {
			filterChain.doFilter(request, response);  // token kontrolü atlanır
			return;
		}
		
		// Header'dan gelen authorization bilgisini alıyoruz
		String authorizationHeader = request.getHeader("Authorization");
		log.debug("Gelen Authorization Header: {}", authorizationHeader);
		
		// Token kontrolü yapıyoruz
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			log.debug("Extracted Token: {}", token);
			
			// Token'ı doğrulayıp TokenInfo alıyoruz
			Optional<TokenInfo> tokenInfoOptional = jwtManager.validateToken(token);
			if (!tokenInfoOptional.isPresent()) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Geçersiz token");
				return;
			}
			if (tokenInfoOptional.isPresent()) {
				TokenInfo tokenInfo = tokenInfoOptional.get();
				UUID authId = tokenInfo.getAuthId();
				RoleName role = tokenInfo.getRole();
				log.debug("Tokenden alınan authId: {}, Rol: {}", authId, role);
				
				// Kullanıcı bilgilerini yükleme işlemi
				UserDetails userDetails = jwtUserDetails.loadUserById(authId);
				
				// Kullanıcının rolünü doğruluyoruz
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
				
				// Token doğrulandıysa, authentication token oluşturuyoruz
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
				
				// Güvenlik bağlamını ayarlıyoruz
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			else {
				log.error("Geçersiz token");
			}
		}
		else {
			log.warn("Authorization header'ı 'Bearer ' ile başlamıyor.");
		}
		
		// FilterChain'e devam ediyoruz
		filterChain.doFilter(request, response);
	}
}