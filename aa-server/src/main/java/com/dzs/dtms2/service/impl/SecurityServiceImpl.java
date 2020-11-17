package com.dzs.dtms2.service.impl;

import com.dzs.dtms2.domain.entity.UserEntity;
import com.dzs.dtms2.repository.UserRepository;
import com.dzs.dtms2.service.SecurityService;
import com.dzs.dtms2.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserRepository repository;

    @Override
    public String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user.getUsername();
    }

    @Override
    public UserEntity getLoggedInUser() {
        String username = this.getLoggedInUsername();
        return CommonUtils.isNullOrEmpty(username) ? null : repository.findByUserName(username);
    }
}
