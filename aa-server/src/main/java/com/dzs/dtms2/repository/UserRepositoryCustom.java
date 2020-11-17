package com.dzs.dtms2.repository;

import com.dzs.dtms2.domain.dto.PaginationDto;

public interface UserRepositoryCustom {

    PaginationDto filter(String username, String fullName, String phoneNumber, String email, int pageSize, int pageNumber);
}
