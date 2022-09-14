package web.springsecurity312.repository;

import org.springframework.stereotype.Repository;
import web.springsecurity312.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class)
                .getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete from User user where user.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
