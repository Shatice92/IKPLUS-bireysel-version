package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveTypeResponse;
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
		return leaveTypeRepository.findAll()
		                          .stream()
		                          .map(LeaveTypeMapper.INSTANCE::toLeaveTypeResponse)
		                          .collect(Collectors.toList());
	}
	
}