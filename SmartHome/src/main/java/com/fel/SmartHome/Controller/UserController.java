package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Model.LoginRequest;
import com.fel.SmartHome.Model.RegisterRequest;
import com.fel.SmartHome.Model.Users;
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

    // GET: Registration Page
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register_page"; // Name of the Thymeleaf template for registration
    }

    // POST: Handle Registration
    @PostMapping("/register")
    public String postRegisterPage(@ModelAttribute RegisterRequest registerRequest, Model model) {
        var registeredUser = userService.registerUser(registerRequest);
        if (registeredUser == null) {
            model.addAttribute("error", "Registration failed. Username already exists.");
            return "register_page"; // Stay on the registration page with an error message
        }
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    // GET: Login Page
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login_page"; // Name of the Thymeleaf template for login
    }

    // POST: Handle Login
    @PostMapping("/login")
    public String postLoginPage(@ModelAttribute LoginRequest loginRequest, Model model) {
        var authenticatedUser = userService.authenticateUser(loginRequest);
        if (authenticatedUser != null) {
            model.addAttribute("user", authenticatedUser);
            return "home_page"; // Redirect to the home page after successful login
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login_page"; // Stay on the login page with an error message
        }
    }

    // GET: Home Page
    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("message", "Hello, welcome to the home page!");
        return "home_page"; // Name of the Thymeleaf template for the home page
    }
}
