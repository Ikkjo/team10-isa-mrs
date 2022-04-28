package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team10.app.model.VacationHome;

import java.util.Optional;

@Repository
public interface VacationHomeRepository extends JpaRepository<VacationHome, Long> {

}
