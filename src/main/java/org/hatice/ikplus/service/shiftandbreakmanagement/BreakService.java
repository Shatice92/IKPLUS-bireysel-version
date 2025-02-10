package org.hatice.ikplus.service.shiftandbreakmanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.shiftandbreakmanagement.BreakRepository;
import org.hatice.ikplus.repository.shiftandbreakmanagement.ShiftRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreakService {
	private final BreakRepository breakRepository;
}