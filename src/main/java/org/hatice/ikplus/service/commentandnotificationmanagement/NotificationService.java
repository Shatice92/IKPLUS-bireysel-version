package org.hatice.ikplus.service.commentandnotificationmanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.commentandnotificationmanagement.CommentRepository;
import org.hatice.ikplus.repository.commentandnotificationmanagement.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
}