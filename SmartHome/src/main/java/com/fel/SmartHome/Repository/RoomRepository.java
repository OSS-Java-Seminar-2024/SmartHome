package com.fel.SmartHome.Repository;

import com.fel.SmartHome.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
