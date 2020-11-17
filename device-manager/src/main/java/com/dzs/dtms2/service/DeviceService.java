package com.dzs.dtms2.service;

import com.dzs.dtms2.domain.dto.DeviceDto;
import com.dzs.dtms2.domain.dto.DeviceSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeviceService {

    List<DeviceDto> getDevices();

    Page<DeviceDto> search(DeviceSearchDto dto);

    DeviceDto getDeviceBySerial(String serial);

    DeviceDto create(DeviceDto dto);

    void update(DeviceDto dto);

    void delete(String serial);
}
