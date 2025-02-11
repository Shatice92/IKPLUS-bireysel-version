package org.hatice.ikplus.controller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.service.HolidayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping()
@RequiredArgsConstructor
public class HolidayController {
	private final HolidayService holidayService;
}