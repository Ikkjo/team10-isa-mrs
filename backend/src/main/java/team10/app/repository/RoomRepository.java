package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
