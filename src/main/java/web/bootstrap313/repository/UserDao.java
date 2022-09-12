package web.bootstrap313.repository;


import org.springframework.stereotype.Repository;
import web.bootstrap313.model.User;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAllUsers();

    void create(User user);

    User getUserById(Long id);

    void delete(Long id);

    void update(User user);

    User findByEmail(String email);
}
