package web.springsecurity312.repository;


import org.springframework.stereotype.Repository;
import web.springsecurity312.model.User;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAllUsers();

    void createUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    void updateUser(User user);

    User findByUsername(String username);
}
