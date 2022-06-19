package team10.app.util.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class ReservationNotFoundException extends EntityNotFoundException {
    public ReservationNotFoundException(UUID id) {
        super(String.format("Reservation not found: %s ", id));
    }
}
