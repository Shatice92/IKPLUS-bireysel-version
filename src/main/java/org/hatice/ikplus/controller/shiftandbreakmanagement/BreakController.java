package org.hatice.ikplus.controller.shiftandbreakmanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.shiftandbreakmanagement.BreakService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.BREAK)
@RequiredArgsConstructor
public class BreakController {
	private final BreakService breakService;
}