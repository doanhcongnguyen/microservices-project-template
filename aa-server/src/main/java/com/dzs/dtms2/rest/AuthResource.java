package com.dzs.dtms2.rest;

import com.dzs.dtms2.domain.dto.ChangePassDto;
import com.dzs.dtms2.service.UserService;
import com.dzs.dtms2.aspect.Log;
import com.dzs.dtms2.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthResource {

    @Autowired
    private UserService userService;

    @Log("POST /change-pass")
    @PostMapping(value = "/change-pass", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Validated ChangePassDto dto) {
        userService.changePass(CommonUtils.getLoggedInUsername(), dto.getNewPassword());
        return new ResponseEntity(HttpStatus.OK);
    }
}
