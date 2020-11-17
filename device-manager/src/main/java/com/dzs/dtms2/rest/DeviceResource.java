package com.dzs.dtms2.rest;

import com.dzs.dtms2.aspect.Log;
import com.dzs.dtms2.dispatcher.MessageDispatcher;
import com.dzs.dtms2.domain.dto.DeviceDto;
import com.dzs.dtms2.domain.dto.DeviceSearchDto;
import com.dzs.dtms2.domain.queue.GeneralMessage;
import com.dzs.dtms2.exception.BadRequestException;
import com.dzs.dtms2.service.DeviceService;
import com.dzs.dtms2.utils.CommonUtils;
import com.dzs.dtms2.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class DeviceResource {

    @Autowired
    private DeviceService service;

    @Autowired
    private MessageDispatcher messageDispatcher;

    @Log("GET /devices")
    @GetMapping("/devices")
    public ResponseEntity getDevices() {
        return new ResponseEntity(service.getDevices(), HttpStatus.OK);
    }

    @Log("GET /device/{serial}")
    @GetMapping("/device/{serial}")
    public ResponseEntity getDevice(@PathVariable String serial) {
        return new ResponseEntity(service.getDeviceBySerial(serial), HttpStatus.OK);
    }

    @GetMapping("/device/sync/{deviceSerial}")
    public ResponseEntity syncDevice(@PathVariable String deviceSerial) {
        String requestId = UUID.randomUUID().toString();
        messageDispatcher.sendMessageToDeviceService(this.createMessage(requestId, deviceSerial));
        return new ResponseEntity(
                CommonUtils.createAsyncResponse(requestId, Constants.JOB.SYNC_DEVICE),
                HttpStatus.OK);
    }

    @Log("POST /device")
    @PostMapping(value = "/device", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Validated DeviceDto dto) {
        this.validateBeforeCreate(dto);
        return new ResponseEntity(service.create(dto), HttpStatus.CREATED);
    }

    @Log("POST /device/search")
    @PostMapping(value = "/device/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity search(@RequestBody DeviceSearchDto dto) {
        this.validateBeforeSearch(dto);
        return new ResponseEntity(service.search(dto), HttpStatus.OK);
    }

    @Log("PUT /device")
    @PutMapping(value = "/device", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Validated DeviceDto dto) {
        this.validateBeforeUpdate(dto);
        service.update(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("DELETE /device")
    @DeleteMapping("/device/{serial}")
    public ResponseEntity delete(@PathVariable String serial) {
        service.delete(serial);
        return new ResponseEntity(HttpStatus.OK);
    }

    private void validateBeforeCreate(DeviceDto dto) {
        if (CommonUtils.isNullOrEmpty(dto.getSerial())) {
            throw new BadRequestException("Serial is null");
        }
    }

    private void validateBeforeUpdate(DeviceDto dto) {
        if (CommonUtils.isNullOrEmpty(dto.getSerial())) {
            throw new BadRequestException("Serial is null");
        }
    }

    private void validateBeforeSearch(DeviceSearchDto dto) {
        if (dto.getPageNumber() <= 0 || dto.getPageSize() <= 0) {
            throw new BadRequestException("Page number and page size must be greater than 0");
        }
    }

    private GeneralMessage createMessage(String requestId, String deviceSerial) {
        return GeneralMessage.builder()
                .requestId(requestId)
                .actionType("SYNC_DEVICE")
                .deviceSerial(deviceSerial)
                .message(new Date() + "")   // TODO: mock here..
                .build();
    }

}
