package team10.app.util;


import java.time.LocalDate;

public class DateTimeUtil {

    public static long getTodayEpochMillisecond() {
        long currentTime = System.currentTimeMillis();
        return currentTime - (currentTime % 86400000L);
    }

}
