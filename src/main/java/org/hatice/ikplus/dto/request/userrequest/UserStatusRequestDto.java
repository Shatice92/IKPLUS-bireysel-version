package org.hatice.ikplus.dto.request.userrequest;

import lombok.Data;
import org.hatice.ikplus.enums.UserStatus;

import java.time.LocalDateTime;


public record UserStatusRequestDto(
		UserStatus status
) {
}