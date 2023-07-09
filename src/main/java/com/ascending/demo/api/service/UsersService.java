package com.ascending.demo.api.service;

import com.ascending.demo.api.dto.UsersDto;
import com.ascending.demo.api.entity.Users;

import java.util.List;

public interface UsersService {
    UsersDto save(UsersDto usersDto);
    UsersDto update(UsersDto usersDto);
    boolean delete(UsersDto usersDto);
    boolean deleteById(Long id);
    List<UsersDto> getAllUsers();

    List<UsersDto> getUsersAndSuppliersByUserId(Long id);
}
