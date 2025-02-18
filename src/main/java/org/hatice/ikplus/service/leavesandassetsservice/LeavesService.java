package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.controller.leavesandassetscontroller.LeavesController;
import org.hatice.ikplus.dto.request.leavesandassetrequest.AddLeaveRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateLeaveRequestDto;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.Leaves;
import org.hatice.ikplus.enums.leaveandassetenums.LeaveStatus;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.LeaveMapper;
import org.hatice.ikplus.repository.leaveandassetrepository.LeavesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeavesService {
	private final LeavesRepository leaveRepository;
	public Leaves createLeave(AddLeaveRequestDto dto) {
		return leaveRepository.save(LeaveMapper.INSTANCE.fromAddLeaveRequestDto(dto));
	}
	
	public LeaveResponse updateLeave(Long id, UpdateLeaveRequestDto dto) {
		Leaves existingLeaveEntity = leaveRepository.findById(id)
		                                            .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_NOT_FOUND));
		LeaveMapper.INSTANCE.updateLeaveFromDto(dto, existingLeaveEntity);
		Leaves savedLeave = leaveRepository.save(existingLeaveEntity);
		return LeaveMapper.INSTANCE.toLeaveResponse(savedLeave);
	}
	
	public void approveLeave(Long id) {
		Leaves leave = leaveRepository.findById(id)
		                              .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_NOT_FOUND));
		if (leave.getStatus() == LeaveStatus.APPROVED) {
			throw new IKPlusException(ErrorType.LEAVE_ALREADY_APPROVED);
		}
		leave.setStatus(LeaveStatus.APPROVED);
		leaveRepository.save(leave);
	}
	
	public void rejectLeave(Long id) {
		Leaves leave = leaveRepository.findById(id)
		                              .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_NOT_FOUND));
		if (leave.getStatus() == LeaveStatus.REJECTED) {
			throw new IKPlusException(ErrorType.LEAVE_ALREADY_REJECTED);
		}
		leave.setStatus(LeaveStatus.REJECTED);
		leaveRepository.save(leave);
	}
	
	public List<LeaveResponse> getAllLeaves() {
		return leaveRepository.findAll().stream()
		                      .map(LeaveMapper.INSTANCE::toLeaveResponse)
		                      .collect(Collectors.toList());
	}
	
	public LeaveResponse getLeaveById(Long id) {
		return leaveRepository.findById(id)
		                      .map(LeaveMapper.INSTANCE::toLeaveResponse)
		                      .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_NOT_FOUND));
	}
	
	public List<LeaveResponse> getLeavesByEmployeeId(Long employeeId) {
		List<Leaves> leaves = leaveRepository.findByEmployeeId(employeeId);
		if (leaves.isEmpty()) {
			throw new IKPlusException(ErrorType.LEAVELIST_EMPTY);
		}
		return leaves.stream()
		             .map(LeaveMapper.INSTANCE::toLeaveResponse)
		             .collect(Collectors.toList());
	}
}