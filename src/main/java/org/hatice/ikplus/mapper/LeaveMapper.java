package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.leavesandassetrequest.AddLeaveRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateLeaveRequestDto;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.LeaveResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.Leaves;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy =
		ReportingPolicy.IGNORE)
public interface LeaveMapper {
	LeaveMapper INSTANCE = Mappers.getMapper(LeaveMapper.class);
	
	@Mapping(target = "status", constant = "PENDING") // Default status is PENDING
	@Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	Leaves fromAddLeaveRequestDto(AddLeaveRequestDto dto);
	
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	void updateLeaveFromDto(UpdateLeaveRequestDto dto, @MappingTarget Leaves entity);
	
	LeaveResponse toLeaveResponse(Leaves leaves);
}