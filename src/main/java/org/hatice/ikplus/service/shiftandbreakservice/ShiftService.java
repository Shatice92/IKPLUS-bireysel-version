package org.hatice.ikplus.service.shiftandbreakservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.AddShiftRequestDto;
import org.hatice.ikplus.entity.shiftandbreakmanagement.Shift;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.ShiftMapper;
import org.hatice.ikplus.repository.shiftandbreakrepository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {
	private final ShiftRepository shiftRepository;
	
	public List<Shift> getShiftByEmployeeId(Long employeeId) {
		List<Shift> shifts = shiftRepository.findByEmployeeId(employeeId);
		if (shifts.isEmpty()) {
			throw new IKPlusException(ErrorType.SHIFT_NOT_FOUND);
		}
		return shifts;
	}
	
	public Shift saveShift(AddShiftRequestDto shiftDto) {
		return shiftRepository.save(ShiftMapper.INSTANCE.fromAddShiftRequestDtoToShift(shiftDto));
	}
}