package web.bootstrap313.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.bootstrap313.model.User;
import web.bootstrap313.service.RoleService;
import web.bootstrap313.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userService.getUserByEmail(userDetails.getUsername()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PatchMapping("/{id}/update")
    public String updateUser(@PathVariable(value = "id", required = false) Long id, @ModelAttribute("user") User user,
                             @RequestParam(value = "nameRole", required = false) String nameRole) {
        if (nameRole != null) {
            user.setRoles(roleService.getByName(nameRole));
        }
        userService.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable(value = "id", required = false) Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/create")
    public String createPage(@AuthenticationPrincipal UserDetails userDetails,Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleName", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserByEmail(userDetails.getUsername()));
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRole", required = false) String nameRole,
                             @RequestParam(value = "email", required = false) String email) {
        user.setEmail(email);
        user.setRoles(roleService.getByName(nameRole));
        userService.add(user);
        return "redirect:/admin";
    }

}
