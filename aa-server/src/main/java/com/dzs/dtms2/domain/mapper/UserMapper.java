package com.dzs.dtms2.domain.mapper;

import com.dzs.dtms2.domain.dto.UserDto;
import com.dzs.dtms2.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDto, UserEntity> {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity entity);
}
