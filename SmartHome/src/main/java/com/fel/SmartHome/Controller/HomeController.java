package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Model.Home;
import com.fel.SmartHome.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/{userId}")
    public String getHomeByUserId(@PathVariable Long userId,Model model) {
        List<Home> homes = homeService.getHomesByUserId(userId);
        model.addAttribute("homes", homes);
        return "home-list";
    }
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("home", new Home());
        return "home-create";
    }
    @PostMapping
    public String createHome(@ModelAttribute("home") Home home) {
        homeService.createHome(home);
        return "home-list";
    }
    @PutMapping("/{id}")
    public String updateHome(@PathVariable Long id,@ModelAttribute Home home) {
        homeService.updateHome(id,home);
        return "home-list";
    }
    @DeleteMapping
    public String deleteHome(@RequestParam("id") Long id) {
        homeService.deleteHome(id);
        return "home-list";
    }



}
