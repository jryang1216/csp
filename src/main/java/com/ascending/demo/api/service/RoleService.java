package com.ascending.demo.api.service;

import com.ascending.demo.api.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto getRoleByName(String name);

    List<RoleDto> getAllRoles();
}
