package org.hatice.ikplus.controller.shiftandbreakcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.entity.shiftandbreakmanagement.Break;
import org.hatice.ikplus.entity.shiftandbreakmanagement.Shift;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.shiftandbreakservice.BreakService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hatice.ikplus.constant.Endpoints.GETBREAKBYEMPLOYEEID;

@RestController
@RequestMapping(Endpoints.EMPLOYEE_BREAKS)
@RequiredArgsConstructor
@CrossOrigin("*")
public class BreakController {
	private final BreakService breakService;
	private final JwtManager jwtManager;
	private final UserService userService;
	
	
	@GetMapping(GETBREAKBYEMPLOYEEID)
	public ResponseEntity<BaseResponse<List<Break>>> getShiftByEmployeeId(@RequestHeader("Authorization") String authorizationHeader) {
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
		List<Break> breaks = breakService.getBreakByEmployeeId(employeeId);
		return ResponseEntity.ok(BaseResponse.<List<Break>>builder().data(breaks)
		                                     .message("Breaks of employee ID " + employeeId + " listed successfully.")
		                                     .code(200).success(true).build());
		
	}
}