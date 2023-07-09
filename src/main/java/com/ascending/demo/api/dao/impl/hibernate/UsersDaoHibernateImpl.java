package com.ascending.demo.api.dao.impl.hibernate;

import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("usersDaoHibernateImpl")
public class UsersDaoHibernateImpl implements UsersDao {

    private Logger logger = LoggerFactory.getLogger(UsersDaoHibernateImpl.class);
    @Override
    public Users save(Users users) {
        return null;
    }

    @Override
    public Users update(Users users) {
        return null;
    }

    @Override
    public boolean delete(Users users) {
        return false;
    }

    @Override
    public boolean deleteById(Long userId) {
        return false;
    }

    @Override
    public List<Users> getAllUsers() {
        return null;
    }

    @Override
    public List<Users> getUsersAndSuppliersByUserId(Long userId) {
        return null;
    }
}
