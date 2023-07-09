package com.ascending.demo.api.daoimpl.springdatajpa;

import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.repository.UsersRepository;
import com.ascending.demo.api.entity.Users;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("usersDaoSpringDataJPAImpl")
public class UsersDaoSpringDataJPAImpl implements UsersDao {
    private Logger logger = LoggerFactory.getLogger(UsersDaoSpringDataJPAImpl.class);

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users save(Users users) {
        Users savedUsers= usersRepository.save(users);
        return savedUsers;
    }

    @Override
    public Users update(Users users) {
        Users updatedUsers = usersRepository.save(users);
        return updatedUsers;
    }

    @Override
    public boolean delete(Users users) {
        boolean successFlag = false;
        try {
            usersRepository.delete(users);
            successFlag = true;
        } catch (IllegalArgumentException iae) {
            logger.error("caught IllegalArgumentException when trying delete user with error = {}", iae.getMessage());
        } catch (OptimisticEntityLockException olfe) {
            logger.error("caught OptimisticEntityLockException when trying delete user with error = {}", olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean successFlag = false;
        try {
            usersRepository.deleteById(id);
            successFlag = true;
        } catch (IllegalArgumentException iae) {
            logger.error("caught IllegalArgumentException when trying deleteById with productId = {}, error = {}", id, iae.getMessage());
        } catch (OptimisticEntityLockException olfe) {
            logger.error("caught OptimisticEntityLockException when trying deleteById with productId = {}, error = {}", id, olfe.getMessage());
        }
        return successFlag;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<Users> getUsersAndSuppliersByUserId(Long id) {
        return null;
    }
}
