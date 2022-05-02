package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team10.app.model.VacationHomeOwner;

import java.util.Optional;

@Repository
@Transactional
public interface VacationHomeOwnerRepository extends JpaRepository<VacationHomeOwner, Long> {

    Optional<VacationHomeOwner> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE WHERE u.email = ?1")
    int enableVacationHomeOwner(String email);

}
