package com.ascending.demo.api.repository;

import com.ascending.demo.api.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ascending.demo.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password) throws UserNotFoundException;

    User findByName(String name);
}
