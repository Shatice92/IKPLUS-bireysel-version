package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.leavesandassetsservice.AssetsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hatice.ikplus.constant.Endpoints.EMPLOYEE_ASSETS;

@RestController
@RequestMapping(Endpoints.EMPLOYEE_ASSETS)
@RequiredArgsConstructor
public class AssetsController {
	private final AssetsService assetsService;
	
}