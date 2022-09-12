package web.bootstrap313.repository;

import org.springframework.stereotype.Repository;
import web.bootstrap313.model.Role;

import java.util.Set;

@Repository
public interface RoleDao {
    Set<Role> getAllRoles();

    Set<Role> getByName(String name);

    void saveRole(Role role);
}
