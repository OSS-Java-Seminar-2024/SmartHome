package com.fel.SmartHome.Repository;

import com.fel.SmartHome.Model.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeRepository extends JpaRepository<Home, Long> {
    List<Home> filterById(long id);
    Optional<Home> findByIdAndUsers_username(long id, String username);
}
