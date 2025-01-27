package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Model.Room;
import com.fel.SmartHome.Model.enums.DeviceStatus;
import com.fel.SmartHome.Model.enums.DeviceType;
import com.fel.SmartHome.Service.DeviceService;
import com.fel.SmartHome.Model.Device;
import com.fel.SmartHome.Service.LogsService;
import com.fel.SmartHome.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final RoomService roomService;
    private final LogsService logsService;

    @Autowired
    public DeviceController(DeviceService deviceService, RoomService roomService, LogsService logsService) {
        this.deviceService = deviceService;
        this.roomService = roomService;
        this.logsService = logsService;
    }

    @GetMapping("/create")
    public String showCreateDeviceForm(Model model) {
        Device device = new Device();
        List<Room> rooms = roomService.getAllRooms(); // sve sobe u padajućem izborniku
        model.addAttribute("device", device);
        model.addAttribute("rooms", rooms);
        model.addAttribute("types", DeviceType.values());
        model.addAttribute("statuses", DeviceStatus.values());
        return "device-form";
    }

    @PostMapping("/create")
    public String createDevice(@ModelAttribute("device") Device device) {
        deviceService.addDevice(device);
        return "redirect:/devices";
    }

    @GetMapping
    public String listDevices(Model model) {
        List<Device> devices = deviceService.getAllDevices();
        model.addAttribute("devices", devices);
        return "device-list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return "redirect:/devices";
    }
    @PostMapping("/update-status/{id}")
    public String updateDeviceStatus(@PathVariable Long id, @RequestParam String status) {
        Device device = deviceService.getDeviceById(id);

        if (device != null) {
            //popravit ovo
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            device.setStatus(DeviceStatus.valueOf(status));
            deviceService.saveDevice(device);

            logsService.logDeviceAction(device, "Updated status to " + status, username);
        }
        return "redirect:/devices";
    }

}
