/*
 *  Copyright 2019, Liwei Wang <daveywang@live.com>.
 *  All rights reserved.
 *  Author: Liwei Wang
 *  Date: 06/2019
 */

package com.ascending.demo.api.service.impl;

import com.ascending.demo.api.dao.RoleDao;
import com.ascending.demo.api.dto.RoleDto;
import com.ascending.demo.api.entity.Role;
import com.ascending.demo.api.service.RoleService;
//import com.ascending.demo.api.util.DtoAndEntityConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService  {
    @Autowired
    @Qualifier("roleSpringDataJPADao")
    private RoleDao roleDao;

    public RoleDto getRoleByName(String name) {
        Role role = roleDao.getRoleByName(name);
//        RoleDto roleDto = DtoAndEntityConvertUtil.convertRoleToRoleDto(role);
        RoleDto roleDto = role.convertRoleToRoleDto();
        return roleDto;
    }

    public List<RoleDto> getAllRoles() {
        List<Role> roleList = roleDao.findAllRoles();
        List<RoleDto> roleDtoList = getRoleDtoListByRoleList(roleList);
        return roleDtoList;
    }

    private List<RoleDto> getRoleDtoListByRoleList(List<Role> roleList) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for(Role role : roleList) {
//            RoleDto roleDto = DtoAndEntityConvertUtil.convertRoleToRoleDto(role);
            RoleDto roleDto = role.convertRoleToRoleDto();
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }
}
