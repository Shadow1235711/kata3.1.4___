package cata311.demo311.service;

import cata311.demo311.Repository.RoleRepository;
import cata311.demo311.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceIml implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceIml(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
