package org.hatice.ikplus.controller.commentandnotificationcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.commentandnotificationresponse.NotificationResponseDto;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.AssetResponseDto;
import org.hatice.ikplus.entity.commentandnotificationmanagement.Notification;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.UserBloodType;
import org.hatice.ikplus.service.commentandnotificationservice.NotificationService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hatice.ikplus.constant.Endpoints.GETNOTIFICATIONSBYEMPLOYEEID;

@RestController
@RequestMapping(Endpoints.NOTIFICATION)
@RequiredArgsConstructor
@CrossOrigin("*")
public class NotificationController {
	private final NotificationService notificationService;
	private final UserService userService;
	private final JwtManager jwtManager;
	
	
	@GetMapping(GETNOTIFICATIONSBYEMPLOYEEID)
	public ResponseEntity<BaseResponse<List<NotificationResponseDto>>> getNotificationsByEmployeeId(@RequestHeader(
			"Authorization") String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		
		TokenInfo tokenInfo = tokenInfoOpt.get();
		UUID authId = tokenInfo.getAuthId();
		
		// authId ile kullanıcıyı bul
		Optional<User> userOpt = userService.findByAuthId(authId);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Kullanıcı bulunamadı
		}
		
		
		User user = userOpt.get();
		Long employeeId = user.getEmployeeId();
		List<NotificationResponseDto> notifications = notificationService.getNotificationByEmployeeId(employeeId);
		return ResponseEntity.ok(BaseResponse.<List<NotificationResponseDto>>builder().data(notifications)
		                                     .message("Notifications of employee ID " + employeeId + " listed " +
				                                              "successfully.")
		                                     .code(200).success(true).build());
	}
}