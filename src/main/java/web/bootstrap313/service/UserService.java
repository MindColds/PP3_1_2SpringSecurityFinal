package web.bootstrap313.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.bootstrap313.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);

    User getUserById(Long id);

    void delete(Long id);

    void update(User user, Long id);

    List<User> getAllUsers();

    User getUserByEmail(String email);
}
