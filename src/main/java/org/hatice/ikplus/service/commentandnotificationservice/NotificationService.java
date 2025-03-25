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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
	
	public List<NotificationResponseDto> getNotificationByUserId(Long userId) {
		List<Notification> notifications = notificationRepository.findByUserId(userId);
		if (notifications.isEmpty()) {
			throw new IKPlusException(ErrorType.NOTIFICATION_NOT_FOUND);
		}
		return notifications.stream().map(NotificationMapper.INSTANCE::toNotificationsResponseDto)
		                    .collect(Collectors.toList());
	}
	
	public void notificationMarkAsRead(Long id) {
		notificationRepository.findById(id).ifPresent(notification -> {
			notification.setIsRead(true);
			notificationRepository.save(notification);
		});
	}
	
	public void deleteNotification(Long id) {
		Optional<Notification> optionalNotification = notificationRepository.findById(id);
		if (optionalNotification.isPresent()) {
			notificationRepository.delete(optionalNotification.get());
		}
		
	}
}