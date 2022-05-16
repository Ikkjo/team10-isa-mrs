package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.Address;
import team10.app.model.Adventure;

import java.util.Optional;

@Repository
public interface AdventureRepository extends  JpaRepository<Adventure, Long>{

    @Query("select a from Adventure a where a.address = ?1")
    Optional<Adventure> findByAddress(Address address);
}
