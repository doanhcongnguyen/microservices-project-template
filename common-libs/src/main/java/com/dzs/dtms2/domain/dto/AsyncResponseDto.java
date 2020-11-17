package com.dzs.dtms2.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AsyncResponseDto {

    private String requestId;
    private String job;
    private String status;
}