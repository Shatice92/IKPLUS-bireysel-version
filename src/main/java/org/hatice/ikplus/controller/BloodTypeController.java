package org.hatice.ikplus.controller;

import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.userresponse.UserProfileResponse;
import org.hatice.ikplus.enums.UserBloodType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.BLOOD_TYPES)
public class BloodTypeController {
	
	@GetMapping
	public ResponseEntity<BaseResponse<UserBloodType[]>> getBloodTypes() {
		return ResponseEntity.ok(BaseResponse.<UserBloodType[]>builder().code(200).data(UserBloodType.values())
		                                     .message("Blood Types are listed successfully").success(true).build());
	}
}