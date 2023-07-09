package com.ascending.demo.api.repository;
import com.ascending.demo.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmailIgnoreCase(String email);

    Users findByLoginName(String name);

    Long deleteByLoginName(String name);
}
