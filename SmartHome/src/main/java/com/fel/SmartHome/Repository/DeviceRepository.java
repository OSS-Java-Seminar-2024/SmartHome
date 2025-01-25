package com.fel.SmartHome.Repository;

import com.fel.SmartHome.Model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
