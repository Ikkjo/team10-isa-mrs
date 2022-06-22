package team10.app.util.exceptions;

public class ReservationsReportParamInvalidException extends RuntimeException {
    public ReservationsReportParamInvalidException(String period) {
        super(String.format("Invalid ReservationReport parameter: %s", period));
    }
}
