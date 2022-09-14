package web.springsecurity312.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.springsecurity312.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    void updateUser(User user, Long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
