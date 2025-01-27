package com.fel.SmartHome.Service;

import com.fel.SmartHome.Model.Device;
import com.fel.SmartHome.Model.Logs;
import com.fel.SmartHome.Repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogsService {
    private final LogsRepository logsRepository;

    @Autowired
    public LogsService(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }

    public List<Logs> getLogsByDevice(Long deviceId) {
        return logsRepository.findByDeviceId(deviceId);
    }

    public List<Logs> searchLogsByAction(String action) {
        return logsRepository.findByActionContainingIgnoreCase(action);
    }

    public void logDeviceAction(Device device, String action, String origin) {
        Logs log = new Logs();
        log.setDevice(device);
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());
        log.setOrigin(origin);
        logsRepository.save(log);
    }

    public List<Logs> getLogsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return logsRepository.findByTimestampBetween(start, end);
    }

    public Logs saveLog(Logs log) {
        return logsRepository.save(log);
    }

    public void deleteLog(Long id) {
        logsRepository.deleteById(id);
    }
}
