package org.hatice.ikplus.dto.request.commentandnotificationrequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactRequestDto(@NotBlank String name,
                                @Email @NotBlank String email,
                                @NotBlank String message) {
}