package org.hatice.ikplus.service.commentandnotificationservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.commentandnotificationrequest.SaveCommentRequestDto;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Comment;
import org.hatice.ikplus.mapper.CommentMapper;
import org.hatice.ikplus.repository.commentandnotificationrepository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	
	public void save(SaveCommentRequestDto dto) {
		commentRepository.save(CommentMapper.INSTANCE.fromSaveCommentRequestDto(dto));
	}
	
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}
}