package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.constant.Endpoints.*;
import org.hatice.ikplus.dto.request.leavesandassetrequest.CreateAssetRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.AssetResponseDto;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.leavesandassetsservice.AssetsService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hatice.ikplus.entity.usermanagement.Authorization;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping(Endpoints.EMPLOYEE_ASSETS)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AssetsController {
	private final AssetsService assetsService;
	private final JwtManager jwtManager;
	private final UserService userService;
	
	
	// TODO bu endpoint sadece employee e açık olacak .
	@GetMapping(GETBYID)//
	public ResponseEntity<BaseResponse<AssetResponseDto>> getAssetById(@PathVariable Long id) {
		return ResponseEntity.ok(BaseResponse.<AssetResponseDto>builder().data(assetsService.findById(id))
		                                     .message("Asset found successfully.").code(200).success(true).build());
	}
	
	@GetMapping(GETASSETBYEMPLOYEEID)
	public ResponseEntity<BaseResponse<List<AssetResponseDto>>> getAssetsByEmployeeId(@RequestHeader("Authorization") String authorizationHeader) {
		// Authorization header'ından token'ı al
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
		
		List<AssetResponseDto> assets = assetsService.getAssetsByEmployeeId(employeeId);
		
		return ResponseEntity.ok(BaseResponse.<List<AssetResponseDto>>builder().data(assets)
		                                     .message("Assets of employee ID " + employeeId + " listed successfully.")
		                                     .code(200).success(true).build());
	}
	
	
	@PutMapping(APPROVE)
	public ResponseEntity<BaseResponse<Boolean>> approveAsset(@PathVariable Long id) {
		assetsService.approveAsset(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Asset approved successfully")
		                                     .code(200).success(true).build());
	}
	
	@PutMapping(REJECT)
	public ResponseEntity<BaseResponse<Boolean>> rejectAsset(@PathVariable Long id) {
		assetsService.rejectAsset(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Asset rejected successfully")
		                                     .code(200).success(true).build());
	}
}