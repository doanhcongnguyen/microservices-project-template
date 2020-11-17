package com.dzs.dtms2.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeviceDto {

    @NotNull
    private String serial;
    private String modelName;
    private String softwareVersion;
    private String status;
}
