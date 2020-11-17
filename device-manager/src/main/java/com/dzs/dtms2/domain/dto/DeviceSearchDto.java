package com.dzs.dtms2.domain.dto;

import lombok.Data;

@Data
public class DeviceSearchDto {

    private String serial;
    private int pageSize;
    private int pageNumber;
}
