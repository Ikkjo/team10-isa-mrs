package team10.app.util.exceptions;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(UUID id) {
        super("User not found: " + id);
    }
}
