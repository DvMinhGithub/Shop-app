package com.project.shopapp.service;

import java.util.List;

import com.project.shopapp.dto.request.RoleRequest;
import com.project.shopapp.model.Role;

public interface RoleService {
    Role createRole(RoleRequest request);

    List<Role> getRoles();

    void deleteRole(Long id);

    Role updateRole(Long id, RoleRequest request);
}
