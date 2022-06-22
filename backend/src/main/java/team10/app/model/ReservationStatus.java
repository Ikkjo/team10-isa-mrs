package team10.app.model;

public enum ReservationStatus {
    CREATED(0),
    ACTIVE(0),
    FINISHED(0),
    REVIEWED(1),
    DIDNT_ARRIVE(1),
    CANCELLED(1),
    DELETED(-1),
    ;

    public final int value;

    ReservationStatus(int value) {
        this.value = value;
    }
}
