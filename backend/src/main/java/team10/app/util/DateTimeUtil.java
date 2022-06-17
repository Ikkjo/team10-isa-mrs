package team10.app.util;


import java.time.LocalDate;

public class DateTimeUtil {

    public static long getTodayEpochMillisecond() {
        return LocalDate.now().toEpochDay() * 1000;
    }

}
