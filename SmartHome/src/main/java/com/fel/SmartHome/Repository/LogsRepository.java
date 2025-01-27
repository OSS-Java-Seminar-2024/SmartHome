package com.fel.SmartHome.Repository;

import com.fel.SmartHome.Model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;

public interface LogsRepository extends JpaRepository<Logs, Long> {
    List<Logs> findByDeviceId(Long deviceId);
    List<Logs> findByActionContainingIgnoreCase(String action);
    List<Logs> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
