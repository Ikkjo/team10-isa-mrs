package team10.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team10.app.model.ShipOwner;

import java.util.Optional;

@Repository
@Transactional(readOnly = true) // provjeriti da li treba
public interface ShipOwnerRepository extends JpaRepository<ShipOwner, Long> {

    Optional<ShipOwner> findByEmail(String email);
    Optional<ShipOwner> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE WHERE u.email = ?1")
    int enableShipOwner(String email);

}
