package com.dzs.dtms2.service;

import com.dzs.dtms2.domain.entity.UserEntity;

public interface SecurityService {

    String getLoggedInUsername();

    UserEntity getLoggedInUser();
}
