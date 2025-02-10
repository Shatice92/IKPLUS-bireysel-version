package org.hatice.ikplus.controller.commentandnotificationmanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.commentandnotificationmanagement.CommentService;
import org.hatice.ikplus.service.shiftandbreakmanagement.BreakService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.COMMENT)
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
}