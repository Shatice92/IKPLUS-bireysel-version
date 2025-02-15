package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.entity.usermanagement.User;
import org.hatice.ikplus.service.usermanagement.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	
	// SaveUserRequestDto'dan User'a dönüşüm
	@Mapping(target = "status", constant = "ACTIVE")
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	User fromSaveUserDto(SaveUserRequestDto dto);
	
	
	@Mapping(target = "status", constant = "ACTIVE")
	@Mapping(target = "authId", expression = "java(java.util.UUID.randomUUID())") // authId ekleniyor
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "roleId", expression = "java(roleService.findRoleIdByName(org.hatice.ikplus.enums.RoleName.WEBSITE_MEMBER))")
	User fromRegisterDto(RegisterRequestDto dto, RoleService roleService);  // RoleService parametre olarak alındı
	
	
	
	
	
	
}