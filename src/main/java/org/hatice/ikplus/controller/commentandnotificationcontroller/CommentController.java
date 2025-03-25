package org.hatice.ikplus.controller.commentandnotificationcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.constant.Endpoints.*;
import org.hatice.ikplus.dto.request.commentandnotificationrequest.SaveCommentRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Comment;
import org.hatice.ikplus.entity.companymanagement.Company;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.commentandnotificationservice.CommentService;
import org.hatice.ikplus.service.companyservice.CompanyService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hatice.ikplus.constant.Endpoints.LIST;
import static org.hatice.ikplus.constant.Endpoints.SAVE;

@RestController
@RequestMapping(Endpoints.COMMENT)
@CrossOrigin("*")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
	private final UserService userService;
	private final CompanyService companyService;
	
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Comment>> saveComment(@RequestHeader("Authorization") String authorizationHeader, @RequestBody SaveCommentRequestDto dto) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token ile kullanıcı bilgilerini al
		Optional<TokenInfo> tokenInfoOpt = userService.getUserProfileByToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		
		TokenInfo tokenInfo = tokenInfoOpt.get();
		Optional<User> userOpt = userService.findByAuthId(tokenInfo.getAuthId());
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Kullanıcı bulunamadı
		}
		
		User user = userOpt.get();
		Company company = companyService.getCompanyByCompanyManagerId(user.getCompanyManagerId());
		Optional<Comment> existingComment = commentService.indByCompanyIdAndCompanyManagerId(company.getId(), user.getCompanyManagerId());
		if (existingComment.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Şirket yöneticisi zaten yorum yapmışsa, yeni yorum eklenemez
		}
		
		
		dto.setCompanyManagerId(user.getCompanyManagerId());
		dto.setUserImageUrl(user.getUserImageUrl());
		
		dto.setCompanyId(company.getId());
		
		return ResponseEntity.ok(BaseResponse.<Comment>builder().data(commentService.save(dto))
		                                     .message("Comment saved successfully").code(200).success(true).build());
	}
	
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<Comment>>> listComments() {
		
		return ResponseEntity.ok(BaseResponse.<List<Comment>>builder().data(commentService.getAllComments())
		                                     .message("Comment saved successfully").code(200).success(true).build());
	}
}