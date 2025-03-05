package org.hatice.ikplus.service.commentandnotificationservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.response.commentandnotificationresponse.NotificationResponseDto;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Notification;
import org.hatice.ikplus.entity.leaveandassetmanagement.Assets;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.AssetMapper;
import org.hatice.ikplus.mapper.NotificationMapper;
import org.hatice.ikplus.repository.commentandnotificationrepository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
	
	public List<NotificationResponseDto> getNotificationByEmployeeId(Long employeeId) {
		List<Notification> notifications=notificationRepository.findByEmployeeId(employeeId);
		if (notifications.isEmpty()) {
			throw new IKPlusException(ErrorType.NOTIFICATION_NOT_FOUND);
		}
		return notifications.stream()
		             .map(NotificationMapper.INSTANCE::toNotificationsResponseDto)
		             .collect(Collectors.toList());
	}
	
	}