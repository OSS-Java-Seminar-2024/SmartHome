package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Model.Schedule;
import com.fel.SmartHome.Service.HomeService;
import com.fel.SmartHome.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
//    private final HomeService homeService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String listSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        return "schedule-list";
    }

    @GetMapping("/create")
    public String showCreateScheduleForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        //model.addAttribute("homes", homeService.getHomeById()); trebalo bi po HomeId pronaci odgovarajuci Home i onda prema RoomId i DeviceId odraditi schedule
        return "schedule-form";
    }

    @PostMapping("/create")
    public String createSchedule(@ModelAttribute Schedule schedule) {
        scheduleService.saveSchedule(schedule);
        return "redirect:/schedules";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedules";
    }
}
