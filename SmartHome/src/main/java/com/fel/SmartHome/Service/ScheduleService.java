package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.Schedule;
import com.fel.SmartHome.Repository.HomeRepository;
import com.fel.SmartHome.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final HomeRepository homeRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, HomeRepository homeRepository) {
        this.scheduleRepository = scheduleRepository;
        this.homeRepository = homeRepository;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> getSchedulesByHome(Long homeId) {
        return scheduleRepository.findByHomeId(homeId);
    }
}
