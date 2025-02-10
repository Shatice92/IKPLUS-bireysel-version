package org.hatice.ikplus.service.commentandnotificationmanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.commentandnotificationmanagement.CommentRepository;
import org.hatice.ikplus.repository.shiftandbreakmanagement.BreakRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
}