package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipRepository extends JpaRepository<Ship, UUID> {

    @Query("select s from Ship s where s.address = ?1")
    Optional<Ship> findByAddress(Address address);

    @Query("select s from Ship s where s.address.address = ?1 and s.address.city = ?2 and s.address.country = ?3")
    Optional<Ship> findByAddress(String address, String city, String country);

    @Query("select s from Ship s where s.owner = ?1 and s.deleted = false")
    List<Ship> findAllByOwner(ShipOwner owner);
}
