package team10.app.util.exceptions;

public class ActionInvalidException extends RuntimeException {
    public ActionInvalidException() {
        super("Invalid action");
    }
}
