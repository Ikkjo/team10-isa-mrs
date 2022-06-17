package team10.app.util;


import java.time.LocalDate;

public class DateTimeUtil {

    public static long getTodayEpochMillisecond() {
        return (System.currentTimeMillis() / 1000L) * 1000;
    }

}
