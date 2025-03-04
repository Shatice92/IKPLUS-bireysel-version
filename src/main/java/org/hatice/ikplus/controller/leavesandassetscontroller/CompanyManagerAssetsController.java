package org.hatice.ikplus.controller.leavesandassetscontroller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.leavesandassetrequest.CreateAssetRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.AssetResponseDto;
import org.hatice.ikplus.service.leavesandassetsservice.AssetsService;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hatice.ikplus.constant.Endpoints.DELETE;
import static org.hatice.ikplus.constant.Endpoints.SAVE;

@RestController
@RequestMapping(Endpoints.COMPANY_MANAGER_ASSETS)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CompanyManagerAssetsController {
	
	private final AssetsService assetsService;
	private final JwtManager jwtManager;
	private final UserService userService;
	
	
	@GetMapping(Endpoints.LIST)
	public ResponseEntity<BaseResponse<List<AssetResponseDto>>> getAllAssets() {
		return ResponseEntity.ok(BaseResponse.<List<AssetResponseDto>>builder().data(assetsService.findAll())
		                                     .message("All assets listed successfully.").code(200).success(true)
		                                     .build());
	}
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<Boolean>> createAsset(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody @Valid CreateAssetRequestDto dto) {
		
		
		// Authorization header'ından token'ı al
		String token = authorizationHeader.replace("Bearer ", "");
		
		// Token'ı doğrula ve authId'yi al
		Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
		if (tokenInfoOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
		}
		TokenInfo tokenInfo = tokenInfoOpt.get();
		// 3. Servis katmanına DTO'yu göndererek kaydetme işlemini başlat
		assetsService.save(dto,tokenInfo);
		
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
				            .data(true)
				            .message("Asset başarıyla oluşturuldu.")
				            .code(200)
				            .success(true)
				            .build()
		);
	}
	
	
	
	@DeleteMapping(DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteAsset(@PathVariable Long id) {
		assetsService.delete(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Asset deleted successfully.")
		                                     .code(200).success(true).build());
	}
}