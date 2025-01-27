package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Service.DeviceService;
import com.fel.SmartHome.Service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/logs")
public class LogsController {
    private final LogsService logsService;
    private final DeviceService deviceService;

    @Autowired
    public LogsController(LogsService logsService, DeviceService deviceService) {
        this.logsService = logsService;
        this.deviceService = deviceService;
    }

    @GetMapping
    public String listLogs(Model model) {
        model.addAttribute("logs", logsService.getAllLogs());
        return "logs-list";
    }

    @GetMapping("/device/{deviceId}")
    public String logsByDevice(@PathVariable Long deviceId, Model model) {
        model.addAttribute("logs", logsService.getLogsByDevice(deviceId));
        model.addAttribute("device", deviceService.getDeviceById(deviceId));
        return "logs-by-device";
    }

    @GetMapping("/search")
    public String searchLogs(@RequestParam(required = false) String action, Model model) {
        if (action != null && !action.isEmpty()) {
            model.addAttribute("logs", logsService.searchLogsByAction(action));
        } else {
            model.addAttribute("logs", logsService.getAllLogs());
        }
        return "logs-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteLog(@PathVariable Long id) {
        logsService.deleteLog(id);
        return "redirect:/logs";
    }
}
