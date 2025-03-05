package org.hatice.ikplus.mapper;


import org.hatice.ikplus.dto.response.commentandnotificationresponse.NotificationResponseDto;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
		ReportingPolicy.IGNORE)
public interface NotificationMapper {
	NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
	
	@Mapping(target = "isRead", expression = "java(false)")
	@Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
	NotificationResponseDto toNotificationsResponseDto(Notification notification);
	
	
	
}