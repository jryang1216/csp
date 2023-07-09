package com.ascending.demo.api.dao;

import com.ascending.demo.api.entity.Users;

import java.util.List;

public interface UsersDao {
    Users save(Users users);
    Users update(Users users);
    boolean delete(Users users);
    boolean deleteById(Long id);
    List<Users> getAllUsers();

    List<Users> getUsersAndSuppliersByUserId(Long id);
}
