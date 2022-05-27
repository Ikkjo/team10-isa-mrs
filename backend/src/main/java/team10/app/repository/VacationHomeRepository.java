package team10.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team10.app.model.Address;
import team10.app.model.VacationHome;
import team10.app.model.VacationHomeOwner;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface VacationHomeRepository extends JpaRepository<VacationHome, UUID> {

    @Query("select vH from VacationHome vH where vH.address = ?1")
    Optional<VacationHome> findByAddress(Address address);

    @Query("select vH from VacationHome vH where vH.address.address = ?1 and vH.address.city = ?2 and vH.address.country = ?3")
    Optional<VacationHome> findByAddress(String address, String city, String country);

    @Query("select vH from VacationHome vH where vH.owner = ?1 and vH.deleted = false")
    List<VacationHome> findAllByOwner(VacationHomeOwner owner);

    @Modifying
    @Query("update VacationHome vH set vH.rooms = ?1 where vH.id = ?2")
    void updateRooms(int rooms, UUID id);

    @Modifying
    @Query("update VacationHome vH set vH.beds = ?1 where vH.id = ?2")
    void updateBeds(int beds, UUID id);
}
