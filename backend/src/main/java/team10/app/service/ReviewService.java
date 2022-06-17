package team10.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team10.app.model.Reservation;
import team10.app.model.ReservationStatus;
import team10.app.repository.ReviewRepository;
import team10.app.util.Validator;
import team10.app.util.exceptions.InvalidReservationBusinessClientException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final Validator validator;


    public void addClientDidntArrive(String businessClientEmail, UUID reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        if (!validator.validateReservationBusinessClient(businessClientEmail, reservation.getBusinessClient().getEmail()))
            throw new InvalidReservationBusinessClientException(reservationId, businessClientEmail);
        reservationService.updateStatus(reservation.getId(), ReservationStatus.DIDNT_ARRIVE);
        clientService.addPenalty(reservation.getClient().getId());
    }
}
