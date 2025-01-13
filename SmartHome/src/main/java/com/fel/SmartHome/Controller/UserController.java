package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Model.User;
import com.fel.SmartHome.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login_page";
    }

    @PostMapping("/register")
    public String postRegisterPage(@ModelAttribute User user) {
        System.out.println("Register request: " + user);
        User registeredUser = userService.registerUser(user);
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String postLoginPage(@ModelAttribute User user, Model model) {
        System.out.println("Login request: " + user);
        User authenticatedUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            model.addAttribute("user", authenticatedUser);
            return "user_page";
        } else {
            return "error_page";
        }
    }
}
