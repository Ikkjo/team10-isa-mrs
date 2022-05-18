package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.User;

import java.util.UUID;

public interface UserJPARepository extends JpaRepository<User, UUID> {
}
