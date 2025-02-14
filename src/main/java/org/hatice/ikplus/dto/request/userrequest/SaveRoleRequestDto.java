package org.hatice.ikplus.dto.request.userrequest;

import org.hatice.ikplus.enums.RoleName;

import java.util.List;

public record SaveRoleRequestDto(RoleName name, List<String> permissions) {
	public SaveRoleRequestDto {
		// Eğer permissions null ise, boş bir liste olarak ayarla
		if (permissions == null) {
			permissions = List.of();  // Boş liste gönder
		}
		
		// Burada name null kontrolü yapılabilir
		if (name == null) {
			throw new IllegalArgumentException("Role name cannot be null");
		}
	}
}