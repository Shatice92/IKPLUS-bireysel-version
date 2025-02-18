package org.hatice.ikplus.dto.request.commentandnotificationrequest;

import jakarta.validation.constraints.NotBlank;

public record SaveCommentRequestDto(String userImageUrl, Long companyId, Long userId,
                                    @NotBlank String comment,Double rating) {
	
}