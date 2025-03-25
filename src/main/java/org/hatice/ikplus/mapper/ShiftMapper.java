package org.hatice.ikplus.mapper;


import org.hatice.ikplus.dto.request.AddShiftRequestDto;
import org.hatice.ikplus.entity.shiftandbreakmanagement.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
		ReportingPolicy.IGNORE)
public interface ShiftMapper {
	ShiftMapper INSTANCE = Mappers.getMapper(ShiftMapper.class);
	
	
	
	Shift fromAddShiftRequestDtoToShift(AddShiftRequestDto addShiftRequestDto);
	
	
	
}