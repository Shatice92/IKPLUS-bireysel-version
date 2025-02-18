package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.commentandnotificationrequest.SaveCommentRequestDto;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
	
	
	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
	
	
	Comment fromSaveCommentRequestDto(SaveCommentRequestDto dto);
}