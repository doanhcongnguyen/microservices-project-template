package com.dzs.dtms2.service.impl;

import com.dzs.dtms2.domain.dto.PaginationDto;
import com.dzs.dtms2.domain.dto.DeviceDto;
import com.dzs.dtms2.domain.dto.DeviceSearchDto;
import com.dzs.dtms2.domain.entity.DeviceEntity;
import com.dzs.dtms2.domain.mapper.DeviceMapper;
import com.dzs.dtms2.repository.DeviceRepository;
import com.dzs.dtms2.service.DeviceService;
import com.dzs.dtms2.utils.PaginationUtils;
import com.dzs.dtms2.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository repository;

    @Autowired
    private DeviceMapper mapper;

    @Override
    public List<DeviceDto> getDevices() {
        List<DeviceEntity> list = repository.findAll();
        return mapper.toDto(list);
    }

    @Override
    public DeviceDto getDeviceBySerial(String serial) {
        DeviceEntity device = repository.findOneBySerial(serial);
        ValidationUtils.isNull(device, "Device", "serial", serial);
        return mapper.toDto(device);
    }

    @Override
    public DeviceDto create(DeviceDto dto) {
        DeviceEntity device = mapper.toEntity(dto);
        device.setIsDeleted(0L);
        DeviceEntity entity = repository.save(device);
        return mapper.toDto(entity);
    }

    @Override
    public Page<DeviceDto> search(DeviceSearchDto dto) {
        PaginationDto paginationDto = repository.filter(dto.getSerial(), dto.getPageSize(), dto.getPageNumber());
        List<DeviceEntity> listEntity = (List<DeviceEntity>) paginationDto.getList();
        List<DeviceDto> listDto = mapper.toDto(listEntity);
        Pageable pageable = PaginationUtils.buildPageable(paginationDto);
        return new PageImpl<>(listDto, pageable, paginationDto.getTotal());
    }

    @Override
    public void update(DeviceDto dto) {
        this.validateIdExists(dto.getSerial());
        DeviceEntity DeviceToUpdate = mapper.toEntity(dto);
        DeviceToUpdate.setIsDeleted(0L);
        repository.save(DeviceToUpdate);
    }

    @Override
    public void delete(String serial) {
        this.validateIdExists(serial);
        repository.deleteBySerial(serial);
    }

    void validateIdExists(String serial) {
        DeviceEntity device = repository.findOneBySerial(serial);
        ValidationUtils.isNull(device, "Device", "serial", serial);
    }
}
