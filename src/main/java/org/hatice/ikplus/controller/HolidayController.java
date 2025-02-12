package org.hatice.ikplus.controller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.service.HolidayService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping()
@RequiredArgsConstructor
@CrossOrigin("*")
public class HolidayController {
	private final HolidayService holidayService;
}