package team10.app.util.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class RentalEntityNotFoundException extends EntityNotFoundException {
    public RentalEntityNotFoundException(UUID id) {
        super(String.format("RentalEntity not found: %s", id));
    }
}
