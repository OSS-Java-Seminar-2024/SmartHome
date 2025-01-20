package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Model.Users;
import com.fel.SmartHome.Service.UserService;
import com.fel.SmartHome.Repository.UserRepository; // Make sure to import UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new Users());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new Users());
        return "login_page";
    }

    @PostMapping("/register")
    public String postRegisterPage(@ModelAttribute Users users) {
        System.out.println("Register request: " + users);
        Users registeredUsers = userService.registerUser(users);
        return registeredUsers == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String postLoginPage(@ModelAttribute Users users, Model model) {
        System.out.println("Login request: " + users);
        Optional<Users> authenticatedUsers = userRepository.findByUsername(users.getUsername());
        if (authenticatedUsers.isPresent() && authenticatedUsers.get().getPassword().equals(users.getPassword())) {
            model.addAttribute("user", authenticatedUsers.get());
            return "user_page";
        } else {
            return "error_page";
        }
    }
}
