package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team10.app.model.BusinessClient;
import team10.app.model.Client;
import team10.app.model.Reservation;
import team10.app.model.ReservationStatus;

import java.util.Set;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query("select r from Reservation r")
    Page<Reservation> findAll(Pageable pageable);

    Page<Reservation> findByBusinessClient(BusinessClient businessClient, Pageable pageable);

    Reservation findReservationByClient(Client client);

    @Modifying
    @Query("update Reservation r set r.status = ?1 where r.id = ?2")
    void updateStatus(ReservationStatus status, UUID id);
}
