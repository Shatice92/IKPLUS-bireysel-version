package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.controller.leavesandassetscontroller.LeavesController;
import org.hatice.ikplus.repository.leaveandassetrepository.LeavesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeavesService {
	private final LeavesRepository leavesRepository;
}