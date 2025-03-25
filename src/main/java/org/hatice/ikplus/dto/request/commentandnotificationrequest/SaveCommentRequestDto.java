package org.hatice.ikplus.dto.request.commentandnotificationrequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveCommentRequestDto {
	@NotBlank
	private String comment;
	@NotNull
	private Double rating;
	private Long companyManagerId;
	private Long companyId;
	private String  userImageUrl;
	
}