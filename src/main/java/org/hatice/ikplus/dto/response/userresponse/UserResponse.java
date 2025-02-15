package org.hatice.ikplus.dto.response.userresponse;

import org.hatice.ikplus.enums.UserStatus;

import java.time.LocalDateTime;

public record UserResponse(UserStatus status, Long roleId, LocalDateTime updatedAt) {
}