package org.hatice.ikplus.service.shiftandbreakservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.shiftandbreakrepository.BreakRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreakService {
	private final BreakRepository breakRepository;
}