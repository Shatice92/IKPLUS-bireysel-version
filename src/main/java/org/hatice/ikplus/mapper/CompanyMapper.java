package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.companyrequest.CompanyRequestDto;
import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.entity.companymanagement.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {
	CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
	
	@Mapping(target = "id", ignore = true) // ID otomatik oluşturulacağı için
	Company toEntity(CompanyRequestDto dto);
	
	CompanyResponse toDto(Company entity);
	
	CompanyResponse toResponse(Company company);
	
}