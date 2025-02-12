package org.hatice.ikplus.service.commentandnotificationservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.commentandnotificationrepository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
}