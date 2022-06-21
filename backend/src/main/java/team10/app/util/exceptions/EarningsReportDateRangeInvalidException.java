package team10.app.util.exceptions;

import java.time.LocalDate;

public class EarningsReportDateRangeInvalidException extends RuntimeException {
    public EarningsReportDateRangeInvalidException(long fromDate, long toDate) {
        super(String.format("Earnings Report date range invalid: %s -> %s", LocalDate.ofEpochDay(fromDate), LocalDate.ofEpochDay(toDate)));
    }
}
