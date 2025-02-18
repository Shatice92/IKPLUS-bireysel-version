package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.leavesandassetsservice.LeavesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.EMPLOYEE_LEAVES)
@RequiredArgsConstructor
public class LeavesController {
	private final LeavesService leavesService;
}