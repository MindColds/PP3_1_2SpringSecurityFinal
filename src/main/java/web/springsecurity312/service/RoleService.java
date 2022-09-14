package web.springsecurity312.service;

import web.springsecurity312.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();

    Set<Role> getRoleByName(String name);

    void saveRole(Role role);
}
