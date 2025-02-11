package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.leaveandassetrepository.LeaveTypesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveTypesService {
	private final LeaveTypesRepository leaveTypesRepository;
}