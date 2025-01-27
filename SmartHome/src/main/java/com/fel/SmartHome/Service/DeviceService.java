package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.Device;
import com.fel.SmartHome.Model.Room;
import com.fel.SmartHome.Model.enums.DeviceStatus;
import com.fel.SmartHome.Repository.DeviceRepository;
import com.fel.SmartHome.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    public Device addDevice(Device device) {
        roomRepository.findById(device.getRoom().getId()).
                orElseThrow(()->new RuntimeException("Room not found" + device.getRoom().getId()));
        return deviceRepository.save(device);
    }
    public Device updateDevice(Long Id,Device device) {
        Device existingDevice = deviceRepository.findById(Id).
                orElseThrow(()->new RuntimeException("The device you are searching for has not been found"));

        roomRepository.findById(device.getRoom().getId()).
                orElseThrow(()->new RuntimeException("Room not found"));
        existingDevice.setName(device.getName());
        existingDevice.setRoom(device.getRoom());
        existingDevice.setType(device.getType());
        existingDevice.setStatus(device.getStatus());

        return deviceRepository.save(existingDevice);
    }

    public void deleteDevice(Long Id) {
        deviceRepository.deleteById(Id);
    }
    
    public List<Device> getDevicesByRoom(Long roomId) {
        return deviceRepository.findByRoomId(roomId);
    }
    public Long countDevicesByRoom(Long roomId) {
        return deviceRepository.countByRoomId(roomId);
    }
    public Device getDeviceById(Long Id) {
        return deviceRepository.findById(Id).
                orElseThrow(()->new RuntimeException("The device not found"));
    }
    public void updateDeviceStatus(Long Id, DeviceStatus status) {
        Device device = deviceRepository.
                findById(Id).orElseThrow(()->new RuntimeException("The device not found"));
        device.setStatus(status);
        deviceRepository.save(device);
    }

    public void saveDevice(Device device) {
        deviceRepository.save(device);
    }
}
