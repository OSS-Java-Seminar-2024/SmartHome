package com.fel.SmartHome.Controller;

import com.fel.SmartHome.Model.Room;
import com.fel.SmartHome.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String ListAllRooms(Model model) {
        model.addAttribute("roomDeviceMap",roomService.getRoomsWithDeviceCount());
        return "room-list";
    }
    @GetMapping("/create")
    public String CreateRoomForm(Model model) {
        model.addAttribute("room",new Room());
        return "room-form";
    }
    @PostMapping("/create")
    public String createRoom(@ModelAttribute("room") Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

}
