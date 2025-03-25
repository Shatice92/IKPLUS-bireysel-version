package org.hatice.ikplus.mapper;


import org.hatice.ikplus.dto.request.commentandnotificationrequest.ContactRequestDto;
import org.hatice.ikplus.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {
	ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
	
	
	@Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
	Contact fromContactRequestDto(ContactRequestDto contactRequestDto);
	
	
	
	
}