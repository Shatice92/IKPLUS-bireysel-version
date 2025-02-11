package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.service.leavesandassetsservice.LeaveTypesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LeaveTypesController {
	private final LeaveTypesService leaveTypesService;
}