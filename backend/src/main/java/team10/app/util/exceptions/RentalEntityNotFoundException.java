package team10.app.util.exceptions;

import java.util.UUID;

public class RentalEntityNotFoundException extends RuntimeException {
    public RentalEntityNotFoundException(UUID id) {
        super(String.format("Could not find RentalEntity %s", id));
    }
}
