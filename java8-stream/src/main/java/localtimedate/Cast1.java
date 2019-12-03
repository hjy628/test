package localtimedate;

import java.time.*;
import java.time.temporal.ChronoField;


/**
 * Created by hjy on 17-6-26.
 */
public class Cast1 {
    public static void main(String[] args) {


/*        LocalDate date = LocalDate.of(2017,6,26);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        System.out.println(date);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(dow);
        System.out.println(len);
        System.out.println(leap);
        System.out.println(LocalDate.now());*/

/*
        LocalDate date = LocalDate.now();
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);*/

    /*    LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
*/
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dt1 = LocalDateTime.of(2016,Month.JUNE,26,14,46,55);
        LocalDateTime dt2 = LocalDateTime.of(date,time);
        LocalDateTime dt3 = date.atTime(14,48);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        System.out.println(dt1);
        System.out.println(dt2);
        System.out.println(dt3);
        System.out.println(dt4);
        System.out.println(dt5);
        System.out.println(date1);
        System.out.println(time1);
    }
}
