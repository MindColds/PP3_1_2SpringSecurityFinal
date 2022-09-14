package web.springsecurity312.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.springsecurity312.model.User;
import web.springsecurity312.repository.UserDao;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.createUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, Long id) {
        User userToBeUpdated = userDao.getUserById(user.getId());
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setPatronim(user.getPatronim());
        userToBeUpdated.setSurname(user.getSurname());
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setRoles(user.getRoles());
        userDao.updateUser(userToBeUpdated);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
        return user;
    }
}
