package org.hatice.ikplus.service.commentandnotificationservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.commentandnotificationrequest.SaveCommentRequestDto;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Comment;
import org.hatice.ikplus.mapper.CommentMapper;
import org.hatice.ikplus.repository.commentandnotificationrepository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	
	public Comment save(SaveCommentRequestDto dto) {
		return commentRepository.save(CommentMapper.INSTANCE.fromSaveCommentRequestDto(dto));
	}
	
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}
	
	public Optional<Comment> indByCompanyIdAndCompanyManagerId(Long companyId, Long companyManagerId) {
		return commentRepository.findByCompanyIdAndCompanyManagerId(companyId, companyManagerId);
	}
}