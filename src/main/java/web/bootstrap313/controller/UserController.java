package web.bootstrap313.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.bootstrap313.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userService.getUserByEmail(userDetails.getUsername()));
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
