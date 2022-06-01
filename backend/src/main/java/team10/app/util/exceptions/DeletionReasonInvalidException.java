package team10.app.util.exceptions;

public class DeletionReasonInvalidException extends RuntimeException {
    public DeletionReasonInvalidException(String deletionReason) {
        super(String.format("DeletionRequest reason invalid: %s", deletionReason));
    }
}
