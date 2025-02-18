package org.hatice.ikplus.controller.commentandnotificationcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.constant.Endpoints.*;
import org.hatice.ikplus.dto.request.commentandnotificationrequest.SaveCommentRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Comment;
import org.hatice.ikplus.service.commentandnotificationservice.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.hatice.ikplus.constant.Endpoints.LIST;
import static org.hatice.ikplus.constant.Endpoints.SAVE;

@RestController
@RequestMapping(Endpoints.COMMENT)
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Boolean>> saveComment(SaveCommentRequestDto dto) {
		commentService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Comment saved successfully")
		                                     .code(200).success(true).build());
	}
	
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<Comment>>> listComments() {
		
		return ResponseEntity.ok(BaseResponse.<List<Comment>>builder().data(commentService.getAllComments())
		                                     .message("Comment saved successfully").code(200).success(true).build());
	}
}