package org.hatice.ikplus.mapper.usermapper;

import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.usermanagement.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	// SaveUserRequestDto'dan User'a dönüşüm
	@Mapping(target = "status", constant = "ACTIVE")
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	User fromSaveUserDto(SaveUserRequestDto dto);
	
	// RegisterRequestDto'dan User'a dönüşüm
	@Mapping(target = "status", constant = "ACTIVE")
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "roleId", expression = "java(roleService.findRoleIdByName(org.hatice.ikplus.enums.RoleName.VISITOR))")
	User fromRegisterDto(RegisterRequestDto dto, RoleService roleService);  // RoleService parametre olarak alındı
}