package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.leavesandassetrequest.CreateAssetRequestDto;
import org.hatice.ikplus.dto.request.leavesandassetrequest.UpdateAssetRequestDto;
import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.leavesandassetsresponse.AssetResponseDto;
import org.hatice.ikplus.entity.leaveandassetmanagement.Assets;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.service.usermanagement.UserService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AssetMapper {
	
	AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);
	
	@Mapping(target = "companyManagerId",
			expression = "java(getCompanyManagerIdFromAuthId(tokenInfo.getAuthId(), userService))")
	@Mapping(target = "status", constant = "INACTIVE")
	@Mapping(target = "assignedDate", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "dueDate", expression = "java(java.time.LocalDateTime.now())")
	Assets fromCreateAssetRequestDto(CreateAssetRequestDto dto, TokenInfo tokenInfo, @Context UserService userService);
	
	void updateAssetFromDto(UpdateAssetRequestDto dto, @MappingTarget Assets asset);
	
	AssetResponseDto toAssetResponseDto(Assets asset);
	
	default Long getCompanyManagerIdFromAuthId(UUID authId, @Context UserService userService) {
		return userService.findByAuthId(authId)
		                  .map(User::getCompanyManagerId)
		                  .orElseThrow(() -> new IKPlusException(ErrorType.COMPANY_MANAGER_NOT_FOUND));
	}
}