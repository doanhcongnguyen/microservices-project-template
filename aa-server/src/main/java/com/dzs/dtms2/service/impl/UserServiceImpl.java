package com.dzs.dtms2.service.impl;

import com.dzs.dtms2.domain.dto.UserDto;
import com.dzs.dtms2.domain.dto.UserSearchDto;
import com.dzs.dtms2.domain.entity.UserEntity;
import com.dzs.dtms2.domain.mapper.UserMapper;
import com.dzs.dtms2.repository.UserRepository;
import com.dzs.dtms2.service.UserService;
import com.dzs.dtms2.domain.dto.PaginationDto;
import com.dzs.dtms2.utils.PaginationUtils;
import com.dzs.dtms2.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> list = repository.findAll();
        return mapper.toDto(list);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<UserEntity> user = repository.findOneByUsername(username);
        ValidationUtils.isNull(user, "User", "username", username);
        return mapper.toDto(user.get());
    }

    @Override
    public UserDto create(UserDto dto) {
        UserEntity userEntity = mapper.toEntity(dto);
        userEntity.setIsDeleted(0L);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity entity = repository.save(userEntity);
        return mapper.toDto(entity);
    }

    @Override
    public Page<UserDto> search(UserSearchDto dto) {
        PaginationDto paginationDto = repository.filter(dto.getUsername(), dto.getFullName(),
                dto.getTelephone(), dto.getEmail(), dto.getPageSize(), dto.getPageNumber());
        List<UserEntity> listEntity = (List<UserEntity>) paginationDto.getList();
        List<UserDto> listDto = mapper.toDto(listEntity);
        Pageable pageable = PaginationUtils.buildPageable(paginationDto);
        return new PageImpl<>(listDto, pageable, paginationDto.getTotal());
    }

    @Override
    public void update(UserDto dto) {
        UserEntity oldUser = this.validateIdExists(dto.getId());
        UserEntity userToUpdate = mapper.toEntity(dto);
        userToUpdate.setPassword(dto.isChangePass() ? passwordEncoder.encode(dto.getPassword()) : oldUser.getPassword());
        userToUpdate.setIsDeleted(0L);
        repository.save(userToUpdate);
    }

    @Override
    public void delete(Long id) {
        this.validateIdExists(id);
        repository.deleteById(id);
    }

    @Override
    public void changePass(String username, String newPassword) {
        Optional<UserEntity> user = repository.findOneByUsername(username);
        ValidationUtils.isNull(user, "User", "username", username);
        UserEntity userToUpdate = user.get();
        userToUpdate.setPassword(passwordEncoder.encode(newPassword));
        repository.save(userToUpdate);
    }

    UserEntity validateIdExists(Long id) {
        Optional<UserEntity> oldUser = repository.findOneById(id);
        ValidationUtils.isNull(oldUser, "User", "id", id);
        return oldUser.get();
    }
}
