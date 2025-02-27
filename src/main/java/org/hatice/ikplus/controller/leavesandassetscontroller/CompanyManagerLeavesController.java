package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.leavesandassetrequest.AddLeaveRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateLeaveRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveResponse;
import org.hatice.ikplus.mapper.LeaveMapper;
import org.hatice.ikplus.service.leavesandassetsservice.LeavesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hatice.ikplus.constant.Endpoints.*;


@CrossOrigin("*")
@RestController
@RequestMapping(COMPANY_MANAGER_LEAVES)
@RequiredArgsConstructor
public class CompanyManagerLeavesController {
	private final LeavesService leaveService;
	
	@PostMapping(SAVE)
	public ResponseEntity<BaseResponse<LeaveResponse>> createLeave(@RequestBody AddLeaveRequestDto dto) {
		LeaveResponse response = LeaveMapper.INSTANCE.toLeaveResponse(leaveService.createLeave(dto));
		return ResponseEntity.ok(BaseResponse.<LeaveResponse>builder()
		                                     .data(response)
		                                     .message("Leave request created successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<LeaveResponse>> updateLeave(@PathVariable Long id,
	                                                               @RequestBody UpdateLeaveRequestDto dto) {
		LeaveResponse response = leaveService.updateLeave(id, dto);
		return ResponseEntity.ok(BaseResponse.<LeaveResponse>builder()
		                                     .data(response)
		                                     .message("Leave updated successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PutMapping(APPROVE)
	public ResponseEntity<BaseResponse<Boolean>> approveLeave(@PathVariable Long id) {
		leaveService.approveLeave(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Leave approved successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PutMapping(REJECT)
	public ResponseEntity<BaseResponse<Boolean>> rejectLeave(@PathVariable Long id) {
		leaveService.rejectLeave(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Leave rejected successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<LeaveResponse>>> getAllLeaves() {
		List<LeaveResponse> leaves = leaveService.getAllLeaves();
		return ResponseEntity.ok(BaseResponse.<List<LeaveResponse>>builder()
		                                     .data(leaves)
		                                     .message("All leaves listed successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping(FINDBYID)
	public ResponseEntity<BaseResponse<LeaveResponse>> getLeaveById(@PathVariable Long id) {
		LeaveResponse response = leaveService.getLeaveById(id);
		return ResponseEntity.ok(BaseResponse.<LeaveResponse>builder()
		                                     .data(response)
		                                     .message("Leave found successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping(GETBYEMPLOYEEID)
	public ResponseEntity<BaseResponse<List<LeaveResponse>>> getLeavesByEmployeeId(@PathVariable Long employeeId) {
		List<LeaveResponse> leaves = leaveService.getLeavesByEmployeeId(employeeId);
		return ResponseEntity.ok(BaseResponse.<List<LeaveResponse>>builder()
		                                     .data(leaves)
		                                     .message("Leaves of employee listed successfully")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
}