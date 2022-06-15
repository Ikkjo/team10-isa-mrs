package team10.app.util.exceptions;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RentalEntityDateTaken extends RuntimeException {
    public RentalEntityDateTaken(UUID id, List<Long> dateRange) {
        super(String.format("RentalEntity date taken: %s, %s - %s", id, LocalDate.ofEpochDay(dateRange.get(0)), LocalDate.ofEpochDay(dateRange.get(1))));
    }
}
