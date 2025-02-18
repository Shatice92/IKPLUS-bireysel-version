package org.hatice.ikplus.controller.shiftandbreakcontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.shiftandbreakservice.BreakService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.COMPANY_MANAGER_BREAK)
@RequiredArgsConstructor
public class BreakController {
	private final BreakService breakService;
}