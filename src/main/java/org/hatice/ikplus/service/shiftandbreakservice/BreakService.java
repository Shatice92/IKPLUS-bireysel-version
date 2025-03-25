package org.hatice.ikplus.service.shiftandbreakservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.entity.shiftandbreakmanagement.Break;
import org.hatice.ikplus.entity.shiftandbreakmanagement.Shift;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.repository.shiftandbreakrepository.BreakRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreakService {
	private final BreakRepository breakRepository;
	
	public List<Break> getBreakByEmployeeId(Long employeeId) {
		List<Break> breaks = breakRepository.findByEmployeeId(employeeId);
		if (breaks.isEmpty()) {
			throw new IKPlusException(ErrorType.BREAK_NOT_FOUND);
		}
		return breaks;
	}
}