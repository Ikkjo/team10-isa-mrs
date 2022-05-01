package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.Address;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;

import java.util.Optional;

@Repository
public interface VacationHomeRepository extends JpaRepository<VacationHome, Long> {

    @Query("select vH from VacationHome vH where vH.address = ?1")
    Optional<VacationHome> findByAddress(Address address);

}
