package com.fel.SmartHome.Repository;

import com.fel.SmartHome.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
