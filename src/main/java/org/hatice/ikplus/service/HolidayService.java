package org.hatice.ikplus.service;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.HolidayRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HolidayService {
	private final HolidayRepository holidayRepository;
}