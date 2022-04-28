package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.RulesOfConduct;

public interface RulesOfConductRepository extends JpaRepository<RulesOfConduct, Long> {
}
