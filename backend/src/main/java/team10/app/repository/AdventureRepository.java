package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.Address;
import team10.app.model.Adventure;
import team10.app.model.Ship;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdventureRepository extends  JpaRepository<Adventure, UUID>{

    @Query("select a from Adventure a where a.address = ?1")
    Optional<Adventure> findByAddress(Address address);

    @Query("select a from Ship a where a.address.address = ?1 and a.address.city = ?2 and a.address.country = ?3")
    Optional<Adventure> findByAddress(String address, String city, String country);
}
