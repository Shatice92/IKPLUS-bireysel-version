package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.leavesandassetrequest.CreateAssetRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateAssetRequestDto;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.AssetResponseDto;
import org.hatice.ikplus.entity.leaveandassetmanagement.Assets;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AssetMapper {
	AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);
	
	@Mapping(target = "assignedDate", expression = "java(java.time.LocalDateTime.now())")
	Assets fromCreateAssetRequestDto(CreateAssetRequestDto dto);
	
	void updateAssetFromDto(UpdateAssetRequestDto dto, @MappingTarget Assets asset);
	
	AssetResponseDto toAssetResponseDto(Assets asset);
}