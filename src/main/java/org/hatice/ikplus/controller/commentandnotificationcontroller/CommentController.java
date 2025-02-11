package org.hatice.ikplus.controller.commentandnotificationcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.commentandnotificationservice.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.COMMENT)
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
}