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

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    Page<Reservation> findByBusinessClient(BusinessClient businessClient, Pageable pageable);

    Reservation findReservationByClient(Client client);

    @Modifying
    @Query("update Reservation r set r.status = ?1 where r.id = ?2")
    void updateStatus(ReservationStatus status, UUID id);

    @Query("select count(r) > 0 from Reservation r where r.rentalEntity.id = ?1 and r.status in ?2")
    boolean existsActiveByRentalEntityId(UUID id, ReservationStatus[] reservationStatuses);

    @Query("select r from Reservation r where r.businessClient = ?1 and r.startDate >= ?2 and r.endDate <= ?3")
    List<Reservation> getAllReservationsByOwnerInRange(BusinessClient businessClient, long fromDate, long toDate);

    @Query("select r from Reservation r where r.endDate >= ?1 and r.endDate <= ?2")
    List<Reservation> getAllInRange(long fromDate, long toDate);

    @Query("select r from Reservation r where r.businessClient = ?1 and r.endDate >= ?2 and r.endDate <= ?3")
    List<Reservation> getAllReservationsByOwnerInRangeForEarnings(BusinessClient byEmail, long fromDate, long toDate);
}
