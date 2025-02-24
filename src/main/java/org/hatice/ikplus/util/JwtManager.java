package org.hatice.ikplus.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.entity.usermanagement.Role;
import org.hatice.ikplus.enums.RoleName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtManager {
	
	@Value("${spring.ikplus.jwt.secret-key}")
	private String secretKey;
	
	@Value("${spring.ikplus.jwt.issuer}")
	private String issuer;
	
	private static String SECRET_KEY;
	private static String ISSUER;
	
	@PostConstruct
	public void init() {
		SECRET_KEY = this.secretKey;
		ISSUER = this.issuer;
	}
	
	private final long EXTIME = 1000L * 60 * 45; // 45 dakika
	
	public String createToken(UUID authId, Role role) {
		Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
		Date creationDate = new Date(System.currentTimeMillis());
		Date expirationDate = new Date(System.currentTimeMillis() + EXTIME);
		
		// Role enum'unun string karşılığını al
		String roleName = role.getName().name();
		
		return JWT.create().withJWTId(UUID.randomUUID().toString())  // Her token benzersiz olur
		          .withIssuer(ISSUER).withIssuedAt(creationDate).withExpiresAt(expirationDate)
		          .withClaim("authId", authId.toString()) // UUID'yi String olarak ekle
		          .withClaim("role", roleName)  // Rolü ekle
		          .sign(algorithm);
	}
	
	
	public Optional<TokenInfo> validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(token);
			
			if (Objects.isNull(decodedJWT)) {
				return Optional.empty();
			}
			
			UUID authId = UUID.fromString(decodedJWT.getClaim("authId").asString());
			String roleString = decodedJWT.getClaim("role").asString();
			RoleName role = RoleName.valueOf(roleString); // String → Enum dönüşümü
			
			return Optional.of(new TokenInfo(authId, role));
		}
		catch (Exception e) {
			return Optional.empty();
		}
	}
	
}