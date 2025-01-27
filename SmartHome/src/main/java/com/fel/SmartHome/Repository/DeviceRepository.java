package com.fel.SmartHome.Repository;

import com.fel.SmartHome.Model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByRoomId(Long roomId);
    Long countByRoomId(Long roomId);
}
