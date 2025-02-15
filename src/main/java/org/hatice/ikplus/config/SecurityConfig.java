package org.hatice.ikplus.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.enums.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
	
	private final JwtTokenFilter jwtTokenFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(req -> req
				// Swagger UI ve api-docs gibi URL'ler için tüm kullanıcılar erişebilir
				.requestMatchers("swagger-ui/**", "/v3/api-docs/**","/v1/dev/user/register" , "/v1/dev/user/login","/v1/dev/user/v1/dev/admin/")
				.permitAll()
				
				// Admin, Company Manager, Employee gibi özel roller için yetkilendirme
				.requestMatchers(Endpoints.ADMIN + "/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(Endpoints.COMPANY_MANAGER + "/**").hasAuthority("ROLE_COMPANY_MANAGER")
				.requestMatchers(Endpoints.EMPLOYEE + "/**").hasAuthority("ROLE_EMPLOYEE")
				.requestMatchers(Endpoints.VISITOR + "/**").hasAuthority("ROLE_VISITOR")
				.requestMatchers(Endpoints.WEBSITE_MEMBER + "/**").hasAuthority("ROLE_WEBSITE_MEMBER")
				
				// Diğer tüm istekler için kimlik doğrulama zorunluluğu
				.anyRequest().authenticated());
		
		// CSRF'yi devre dışı bırakıyoruz
		http.csrf(AbstractHttpConfigurer::disable);
		
		// JWT Filtresini UsernamePasswordAuthenticationFilter'dan önce çalıştır
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}