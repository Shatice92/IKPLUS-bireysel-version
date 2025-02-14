package org.hatice.ikplus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.RoleName;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {
	private UUID authId; // UUID olarak değiştirdik
	private RoleName role;
}