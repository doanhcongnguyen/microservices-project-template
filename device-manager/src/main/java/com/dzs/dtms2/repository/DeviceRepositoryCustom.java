package com.dzs.dtms2.repository;

import com.dzs.dtms2.domain.dto.PaginationDto;

public interface DeviceRepositoryCustom {

    PaginationDto filter(String serial, int pageSize, int pageNumber);
}
