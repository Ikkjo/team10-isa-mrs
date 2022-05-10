package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.Address;
import team10.app.model.Ship;
import team10.app.model.VacationHome;

import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    @Query("select s from Ship s where s.address = ?1")
    Optional<Ship> findByAddress(Address address);
}
