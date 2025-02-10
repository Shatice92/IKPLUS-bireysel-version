package org.hatice.ikplus.controller.shiftandbreakmanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.shiftandbreakmanagement.ShiftService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.SHIFT)
@RequiredArgsConstructor
public class ShiftController {
	private final ShiftService shiftService;
}