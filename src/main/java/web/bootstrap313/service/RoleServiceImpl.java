package web.bootstrap313.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.bootstrap313.model.Role;
import web.bootstrap313.repository.RoleDao;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Set<Role> getByName(String name) {
        Set<Role> roles = new HashSet<>();
        for (Role role : getAllRoles()) {
            if (name.contains(role.getName())) {
                roles.add(role);
            }
        }
        return roles;
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }
}
