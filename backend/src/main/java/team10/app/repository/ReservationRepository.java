package team10.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team10.app.model.Client;
import team10.app.model.Reservation;

import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findReservationById(Long id);

    Page<Reservation> findAll(Pageable pageable);

    Reservation findReservationByClient(Client client);
}
