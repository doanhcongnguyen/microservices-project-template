package com.dzs.dtms2.aspect;

import lombok.Data;

@Data
public class RequestDto {

    private String requestId;
    private String api;
    private String ip;
    private int status;
    private String time;
}
