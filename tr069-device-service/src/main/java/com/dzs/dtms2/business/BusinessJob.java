package com.dzs.dtms2.business;

import com.dzs.dtms2.dispatcher.MessageDispatcher;
import com.dzs.dtms2.domain.dto.DeviceDto;
import com.dzs.dtms2.domain.queue.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Component
public class BusinessJob {

    @Value("${api.device-manager-url}")
    private String deviceManagerApiUrl;

    @Autowired
    private MessageDispatcher messageDispatcher;

    @Autowired
    private RestTemplate restTemplate;

    public void syncDevice(GeneralMessage nbiIncomeMessage) {
        this.syncBusiness(nbiIncomeMessage);
        messageDispatcher.sendMessageToDeviceService(nbiIncomeMessage);
    }

    public void syncBusiness(GeneralMessage nbiIncomeMessage) {
        DeviceDto dto = createDto(nbiIncomeMessage);
        restTemplate.put(deviceManagerApiUrl, dto);
    }

    public DeviceDto createDto(GeneralMessage generalMessage) {
        return DeviceDto.builder()
                .modelName(UUID.randomUUID().toString())
                .serial(generalMessage.getDeviceSerial())
                .softwareVersion(UUID.randomUUID().toString())
                .status(UUID.randomUUID().toString())
                .build();
    }
}
