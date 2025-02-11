package org.hatice.ikplus.service.commentandnotificationservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.commentandnotificationrepository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
}