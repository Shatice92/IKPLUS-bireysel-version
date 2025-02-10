package org.hatice.ikplus.controller.commentandnotificationmanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.commentandnotificationmanagement.NotificationService;
import org.hatice.ikplus.service.shiftandbreakmanagement.BreakService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.NOTIFICATION)
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;
}