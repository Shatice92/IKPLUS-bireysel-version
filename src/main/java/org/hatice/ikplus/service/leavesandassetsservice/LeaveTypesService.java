package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.leavesandassetrequest.AddLeaveTypeRequestDto;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveTypeResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.LeaveTypeMapper;
import org.hatice.ikplus.repository.leaveandassetrepository.LeaveTypesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveTypesService {
	private final LeaveTypesRepository leaveTypeRepository;
	
	public List<LeaveTypeResponse> getAllLeaveTypes() {
		return leaveTypeRepository.findAll().stream().map(LeaveTypeMapper.INSTANCE::toLeaveTypeResponse)
		                          .collect(Collectors.toList());
	}
	
	public Long getLeaveTypeIdByName(TypeLeaves leaveName) {
		return leaveTypeRepository.findByLeavesName(leaveName).map(LeaveTypes::getId)
		                          .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_NOT_FOUND));
	}
	
	
	public LeaveTypes save(LeaveTypes leaveTypes) {
		return leaveTypeRepository.save(leaveTypes);
	}
}