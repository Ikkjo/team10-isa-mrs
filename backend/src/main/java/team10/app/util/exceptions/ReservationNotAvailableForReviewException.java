package team10.app.util.exceptions;

import java.util.UUID;

public class ReservationNotAvailableForReviewException extends RuntimeException {
    public ReservationNotAvailableForReviewException(UUID id) {
        super("Reservation not avaliable for review: "+id);
    }
}
