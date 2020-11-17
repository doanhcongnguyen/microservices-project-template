package com.dzs.dtms2.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceDto {

    private String serial;
    private String modelName;
    private String softwareVersion;
    private String status;
}
