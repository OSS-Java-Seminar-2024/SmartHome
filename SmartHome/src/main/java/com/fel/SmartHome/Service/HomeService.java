package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.Home;
import com.fel.SmartHome.Repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {
private final HomeRepository homeRepository;

@Autowired
    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }
    public List<Home> getHomesByUserId(Long userId) {
    //filtriranje Home-a po userId-u
        return homeRepository.findAll().stream()
                .filter(home -> home.getUsers().stream().anyMatch(users -> users.getId().equals(userId)))
                .collect(Collectors.toList());
    }
    public Home getHomeById(Long homeId) {
    return homeRepository.findById(homeId)
            .orElseThrow(() -> new RuntimeException("Home not found"));
    }
    public Home createHome(Home home) {
    return homeRepository.save(home);
    }
    public Home updateHome(Long id,Home updatedHome) {
    Home home = getHomeById(id);
    home.setOwner(updatedHome.getOwner());
    home.setLocation(updatedHome.getLocation());
    home.setRooms(updatedHome.getRooms());
    return homeRepository.save(home);
    }

    public void deleteHome(Long homeId) {
    homeRepository.deleteById(homeId);
    }

}
