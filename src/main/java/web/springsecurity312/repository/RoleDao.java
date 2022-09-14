package web.springsecurity312.repository;

import org.springframework.stereotype.Repository;
import web.springsecurity312.model.Role;

import java.util.Set;

@Repository
public interface RoleDao {
    Set<Role> getAllRoles();

    Set<Role> getByName(String name);

    void saveRole(Role role);
}
