package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {

    Action findActionById(Long id);

}
