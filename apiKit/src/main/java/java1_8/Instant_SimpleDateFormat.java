package java1_8;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Create by Dove on 2019/9/28 23:51
 */
public class Instant_SimpleDateFormat {
    public static void main(String[] args) {
        //Instant 代替 Date
        Instant now = Instant.now();
        long epochSecond = now.getEpochSecond();//获取当前毫秒值
        System.out.println(epochSecond);//1574478518
        System.out.println(now);//2019-11-23T03:08:38.571Z
        System.out.println(now.getEpochSecond());//1574478518
        //DateTimeFormatter 代替 SimpleDateFormat 线程不安全
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //LocalDateTime 代替 calendar
        LocalDateTime calender = LocalDateTime.of(2018, 8, 8, 8, 8);
        System.out.println(calender);
        String format = dateTimeFormatter.format(calender);
        System.out.println(format);

        LocalDate date = LocalDate.parse("2088/12/12", dateTimeFormatter);
        System.out.println(date);

        LocalDate now1 = LocalDate.now();
        System.out.println(now1);//2019-11-23
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(now2);//2019-11-23T11:05:56.092

        LocalDate date1 = LocalDate.of(2099, 8, 12);
        System.out.println(date1.toString());
    }
}
