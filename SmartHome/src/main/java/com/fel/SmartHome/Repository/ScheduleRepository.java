package com.fel.SmartHome.Repository;

import com.fel.SmartHome.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByHomeId(Long homeId);
}
