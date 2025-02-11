package org.hatice.ikplus.service.shiftandbreakservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.shiftandbreakrepository.ShiftRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShiftService {
	private final ShiftRepository shiftRepository;
	
}