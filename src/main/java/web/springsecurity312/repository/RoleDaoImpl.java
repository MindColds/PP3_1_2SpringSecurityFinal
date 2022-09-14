package web.springsecurity312.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.springsecurity312.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role  role", Role.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Role> getByName(String name) {
        return entityManager.createQuery("select role from Role role where role.name = :name", Role.class)
                .setParameter("name", name)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
