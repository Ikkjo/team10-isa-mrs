package team10.app.util.exceptions;

import java.util.UUID;

public class RentalEntityReservedException extends RuntimeException {
    public RentalEntityReservedException(UUID id) {
        super(String.format("Unable to delete RentalEntity: %s, with active reservations", id));
    }
}
