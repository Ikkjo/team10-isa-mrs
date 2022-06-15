package team10.app.util.exceptions;

import java.util.UUID;

public class InvalidRentalEntityOwnerException extends RuntimeException {
    public InvalidRentalEntityOwnerException(UUID rentalEntityId, UUID ownerId) {
        super(String.format("Invalid owner: %s, of rental entity: %s", ownerId, rentalEntityId));
    }
}
