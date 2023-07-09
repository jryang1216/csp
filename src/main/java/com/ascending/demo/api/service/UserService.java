package com.ascending.demo.api.service;

import com.ascending.demo.api.dto.UserDto;
//import com.ascendingdc.training.project2020.entity.User;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getUserByEmail(String email);

    UserDto getUserById(Long userid);

    UserDto getUserByCredentials(String email, String password);

    List<UserDto> getAllUsers();


}
