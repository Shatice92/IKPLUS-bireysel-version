package org.hatice.ikplus.dto.response.commentandnotificationresponse;

import java.time.LocalDateTime;

public record NotificationResponseDto(Long id,String title, String notification, LocalDateTime createdAt, Boolean isRead,
                                      String notificationType) {
}