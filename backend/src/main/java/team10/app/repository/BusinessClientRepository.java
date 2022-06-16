package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team10.app.model.Address;
import team10.app.model.BusinessClient;
import team10.app.model.Reservation;

import java.util.Optional;
import java.util.UUID;

public interface BusinessClientRepository extends JpaRepository<BusinessClient, UUID> {

    Optional<BusinessClient> findByEmail(String email);

    @Modifying
    @Query("update BusinessClient bC set bC.address = ?1 where bC.email = ?2")
    void updateAddress(Address address, String email);

    @Modifying
    @Query("update BusinessClient bC set bC.dateOfBirth = ?1 where bC.email = ?2")
    void updateDateOfBirth(String dateOfBirth, String email);

    @Query(value = "select bC.reservations from BusinessClient bC where bC.id = ?1", nativeQuery = true)
    Page<Reservation> findAllReservationsWithPagination(UUID id, Pageable pageable);

}
