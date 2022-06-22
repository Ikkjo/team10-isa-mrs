package team10.app.util.exceptions;

public class DeletionRequestAlreadyPresentException extends RuntimeException {
    public DeletionRequestAlreadyPresentException(String email) {
        super(String.format("Account deletion already requested: %s", email));
    }
}
