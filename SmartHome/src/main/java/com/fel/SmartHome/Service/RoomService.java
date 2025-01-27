package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.Room;
import com.fel.SmartHome.Repository.DeviceRepository;
import com.fel.SmartHome.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, DeviceRepository deviceRepository) {
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }
    public void deleteRoom(Long id) {
        long deviceCount = getDeviceCount(id);
        if (deviceCount > 0) {
            throw new RuntimeException("Room cannot be deleted because there are " + deviceCount + " devices in this room");
        }
        roomRepository.deleteById(id);
    }
    public Long getDeviceCount(Long roomId) {
        return deviceRepository.countByRoomId(roomId);
    }
    public Map<Room,Long> getRoomsWithDeviceCount(){
        List<Room> rooms = roomRepository.findAll();
        Map<Room,Long> roomDeviceCountMap = new HashMap<>();
        for (Room room : rooms) {
            long deviceCount = getDeviceCount(room.getId());
            roomDeviceCountMap.put(room,deviceCount);
        }
        return roomDeviceCountMap;
    }
}
