package cata311.demo311.service;

import cata311.demo311.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void saveRole(Role role);

    Role findRoleById(Long id);

}
