package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;
import org.hatice.ikplus.service.leavesandassetsservice.LeaveTypesService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@CrossOrigin("*")
@RequestMapping(Endpoints.EMPLOYEE_LEAVES)
@RequiredArgsConstructor
public class LeaveTypesController {
	private final LeaveTypesService leaveTypesService;
	private final JwtManager jwtManager;
	
	@GetMapping(Endpoints.GETLEAVEIDBYLEAVETYPENAME)
	public ResponseEntity<BaseResponse<Long>> getLeaveIdByLeaveTypeName(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("leaveName") TypeLeaves leaveName) {
		Long leaveId = leaveTypesService.getLeaveTypeIdByName(leaveName);
		return ResponseEntity.ok(BaseResponse.<Long>builder().data(leaveId).success(true).code(200)
		                                     .message("Leavetype ıd found successfully.").build());
		
	}
	
	
}