package org.hatice.ikplus.controller.commentandnotificationcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.commentandnotificationresponse.NotificationResponseDto;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.commentandnotificationservice.NotificationService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hatice.ikplus.constant.Endpoints.*;

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
		Long userId = user.getId();
		List<NotificationResponseDto> notifications = notificationService.getNotificationByUserId(userId);
		return ResponseEntity.ok(BaseResponse.<List<NotificationResponseDto>>builder().data(notifications)
		                                     .message("Notifications of user ID " + userId + " listed " +
				                                              "successfully.")
		                                     .code(200).success(true).build());
	}
	
	
	@PutMapping(MARKASREAD)
	public ResponseEntity<BaseResponse<Boolean>> notificationMarkAsRead(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("id") Long id) {
		String token = authorizationHeader.replace("Bearer ", "");
		
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BaseResponse.<Boolean>builder()
			                                                                    .code(403)
			                                                                    .message("Geçersiz token")
			                                                                    .data(false)
			                                                                    .build());
		}
		
		notificationService.notificationMarkAsRead(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .message("Bildirim okundu olarak işaretlendi.")
		                                     .data(true)
		                                     .build());
	}
	
	
	
	@DeleteMapping(DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteNotification(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("id") Long id) {
		String token = authorizationHeader.replace("Bearer ", "");
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		TokenInfo tokenInfo = tokenInfoOpt.get();
		notificationService.deleteNotification(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .code(200)
		                                     .message("Notification deleted successfully.")
		                                     .data(true)
		                                     .build());
		
	}
	
}