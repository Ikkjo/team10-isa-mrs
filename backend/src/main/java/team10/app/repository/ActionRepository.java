package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Action;

import java.util.UUID;

public interface ActionRepository extends JpaRepository<Action, UUID> {

    Action findActionById(UUID id);

}
