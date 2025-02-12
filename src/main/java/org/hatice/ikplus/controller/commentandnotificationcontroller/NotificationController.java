package org.hatice.ikplus.controller.commentandnotificationcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.commentandnotificationservice.NotificationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.NOTIFICATION)
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;
}