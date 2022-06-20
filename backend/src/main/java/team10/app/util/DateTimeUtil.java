package team10.app.util;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    private static final long MS_IN_DAY = 86400000L;

    public static long getTodayEpochMillisecond() {
        long currentTime = System.currentTimeMillis();
        return currentTime - (currentTime % 86400000L);
    }


    /**
     * Returns Epoch milliseconds of today + days
     * @param days Difference in days from today
     * @return Epoch milliseconds of today + days
     */
    public static long getDeltaFromToday(int days) {
        return getTodayEpochMillisecond() - days * MS_IN_DAY;
    }

    /**
     * Returns Epoch milliseconds of delta weeks from today, starting from monday.
     * @param weeks Difference in weeks from today
     * @return Epoch milliseconds of today + weeks
     */
    public static long getDeltaWeekFromToday(int weeks) {
        long currentTime = System.currentTimeMillis();
        if (weeks < 0)
            return currentTime - (currentTime % (604800000L * (weeks+1))) - MS_IN_DAY*3;
        else
            return currentTime + (currentTime % (604800000L * (weeks+1))) - MS_IN_DAY*3;
    }

    /**
     *
     * @return First day of current year in epoch milliseconds
     */
    public static long getFirstDayOfCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return cal.getTimeInMillis();
    }

    /**
     *
     * @return Last day of current year in epoch milliseconds
     */
    public static long getLastDayOfCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 11); // 11 = decembar
        cal.set(Calendar.DAY_OF_MONTH, 31);
        return cal.getTimeInMillis();
    }

    public static String getMonthFromDate(long day) {
        Date date = new Date(day);
        return new SimpleDateFormat("MMMM").format(date);
    }

    public static String getYearFromDate(long day) {
        Date date = new Date(day);
        return new SimpleDateFormat("yyyy").format(date);
    }

    public static String getWeekOfYearFromDate(long endDate) {
        LocalDate day = Instant.ofEpochMilli(endDate).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate epoch = LocalDate.ofEpochDay(0);
        return String.valueOf(ChronoUnit.WEEKS.between(epoch, day));
    }

    public static boolean sameMonthAndYear(long date1, long date2) {
        return getMonthFromDate(date1).equals(getMonthFromDate(date2))
                && getYearFromDate(date1).equals(getYearFromDate(date2));
    }

    public static String getMonthAndYearFromDate(long date) {
        return getMonthFromDate(date) + " " + getYearFromDate(date);
    }

    public static String getDateFromEpochMilliseconds(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(new Date(date));
    }
}
