package org.hatice.ikplus.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hatice.ikplus.constant.Endpoints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
	
	private final @Lazy JwtTokenFilter jwtTokenFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		    .csrf(csrf -> csrf.disable())
		    .authorizeHttpRequests(req -> req
				    // Register ve Login işlemleri için herkese açık izin

				    .requestMatchers("swagger-ui/**", "/v3/api-docs/**", "/v1/dev/user/register", "/v1/dev/user/login", "/v1/dev/user/get-profile-by-token",
				                     "/v1/dev/user/update-user-profile", "/v1/dev/user/update-status", "/v1/dev/blood" +
						                     "-types", "/v1/dev/user/verify/**", "/v1/dev/password/reset", "/v1/dev" +
						                     "/user/dashboard", "/v1/dev/password/request", "/reset-password.html",
				                     "/v1/dev/notification/mark-as-read","/v1/dev/notification/get-notifications-by" +
						                     "-employeeId","/v1/dev/comment/list","/v1/dev/notification/delete/","/v1/dev/contact/save")

				    .permitAll()
				    
				    
				    // Admin, Company Manager, Employee gibi özel roller için yetkilendirme
				    .requestMatchers(Endpoints.ADMIN + "/**").hasAuthority("ROLE_ADMIN")
				    .requestMatchers(Endpoints.COMPANY_MANAGER + "/**").hasAuthority("ROLE_COMPANY_MANAGER")
				    .requestMatchers(Endpoints.EMPLOYEE + "/**").hasAuthority("ROLE_EMPLOYEE")
				    .requestMatchers(Endpoints.VISITOR + "/**").hasAuthority("ROLE_VISITOR")
				    .requestMatchers(Endpoints.WEBSITE_MEMBER + "/**").hasAuthority("ROLE_WEBSITE_MEMBER")
				    
				    // Diğer tüm istekler için kimlik doğrulama zorunluluğu
				    .anyRequest().authenticated())
		    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ JWT için session yönetimi
		    .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class); // ✅ JWT Filtreleme
		
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:3000")); // ✅ FRONTEND DOMAINİNİ EKLE
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		
		// OPTIONS isteklerinin preflight yapılmasını engellemek için aşağıdaki ayarları ekleyelim
		configuration.setExposedHeaders(List.of("Authorization")); // 👈 Frontend'in token'ı görmesini sağlar.
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}