package com.dzs.dtms2.domain.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMessage {

    private String message;
    private String actionType;
    private String deviceSerial;
    private String requestId;
}