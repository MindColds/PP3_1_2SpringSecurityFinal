package web.springsecurity312.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.springsecurity312.model.Role;
import web.springsecurity312.model.User;
import web.springsecurity312.service.RoleService;
import web.springsecurity312.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DefaultUser {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DefaultUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void initialize() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);
        User admin = new User();
        admin.setName("Admin");
        admin.setPatronim("Sysopovich");
        admin.setSurname("Adminov");
        admin.setAge(41);
        admin.setEmail("admin@mail.ru");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.addRole(adminRole);
        admin.addRole(userRole);
        userService.addUser(admin);
    }
}
