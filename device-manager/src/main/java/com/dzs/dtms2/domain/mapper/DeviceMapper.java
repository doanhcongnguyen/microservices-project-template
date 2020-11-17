package com.dzs.dtms2.domain.mapper;

import com.dzs.dtms2.domain.dto.DeviceDto;
import com.dzs.dtms2.domain.entity.DeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeviceMapper extends EntityMapper<DeviceDto, DeviceEntity> {

}
