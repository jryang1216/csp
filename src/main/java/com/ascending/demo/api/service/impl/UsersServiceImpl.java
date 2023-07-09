package com.ascending.demo.api.service.impl;

import com.ascending.demo.api.dao.SuppliersDao;
import com.ascending.demo.api.dao.UsersDao;
import com.ascending.demo.api.dto.SuppliersDto;
import com.ascending.demo.api.dto.UsersDto;
import com.ascending.demo.api.entity.Suppliers;
import com.ascending.demo.api.entity.Users;
import com.ascending.demo.api.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UsersServiceImpl implements UsersService {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    @Qualifier("suppliersDaoSpringDataJPAImpl")
    private SuppliersDao suppliersDao;

    @Autowired
    @Qualifier("usersDaoSpringDataJPAImpl")
    private UsersDao usersDao;


    @Override
    public UsersDto save(UsersDto usersDto) {
        Users users = usersDto.convertUsersDtoToUsers();
        Users savedUser = usersDao.save(users);
        UsersDto savedUsersDto = savedUser.convertUsersToUsersDto();
        return savedUsersDto;
    }

    @Override
    public UsersDto update(UsersDto usersDto) {
        Users users = usersDto.convertUsersDtoToUsers();
        Users updatedUser = usersDao.update(users);
        UsersDto updatedUserDto = updatedUser.convertUsersToUsersDto();
        return updatedUserDto;
    }

    @Override
    public boolean delete(UsersDto usersDto) {
        Users users = usersDto.convertUsersDtoToUsers();
        boolean deleteResult = usersDao.delete(users);
        return deleteResult;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean deleteResult = usersDao.deleteById(id);
        logger.debug("====within method UsersServiceImpl.deleteById(...), majorId = {}, deleteResult = {}", id, deleteResult);
        return deleteResult;
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> usersList = usersDao.getAllUsers();
        List<UsersDto> usersDtoList = getUsersDtoListFromUsersList(usersList);
        return usersDtoList;
    }

    private List<UsersDto> getUsersDtoListFromUsersList(List<Users> usersList) {
        List<UsersDto> usersDtoList = new ArrayList<>();
        for (Users users : usersList) {
            UsersDto usersDto = users.convertUsersToUsersDto();
            usersDtoList.add(usersDto);
        }
        return usersDtoList;
    }

    @Override
    public List<UsersDto> getUsersAndSuppliersByUserId(Long id) {
//        Users retrievedUsers = usersDao.getUsersAndSuppliersByUserId(id);
//        UsersDto retrievedUsersDto = retrievedUsers.convertUsersToUsersDto();
//        return retrievedUsersDto;
        return null;
    }
}
