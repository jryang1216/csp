package com.ascending.demo.api.dao;

import com.ascending.demo.api.entity.Role;
import com.ascending.demo.api.entity.User;
import com.ascending.demo.api.exception.UserNotFoundException;

import java.util.List;

public interface UserDao {
    User save(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
    User getUserByCredentials(String email, String password) throws UserNotFoundException;
    User addRole(User user, Role role);
    boolean delete(User user);
    List<User> findAllUsers();
    User getUserByName(String username);
}
