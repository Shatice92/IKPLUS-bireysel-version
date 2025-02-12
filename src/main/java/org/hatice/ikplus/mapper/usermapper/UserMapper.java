package org.hatice.ikplus.mapper.usermapper;

import org.hatice.ikplus.dto.request.userrequest.RegisterRequestDto;
import org.hatice.ikplus.dto.request.userrequest.SaveUserRequestDto;
import org.hatice.ikplus.entity.usermanagement.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", // Springde çalışması için gerekli.
		unmappedTargetPolicy = ReportingPolicy.IGNORE, // Eğer DTO'daki bir alan User Entity'sine eşleşmiyorsa, MapStruct bu durumu görmezden gelir ve hata üretmez.
		unmappedSourcePolicy = ReportingPolicy.IGNORE) // Aynı şekilde, RegisterRequestDto içindeki ancak User tarafında eşlenmeyen alanlar için hata oluşturulmaz.
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	
	User fromSaveUserDto(SaveUserRequestDto dto);
	
	User fromRegisterDto(RegisterRequestDto dto);
	
}