package me.whiteship.java8to11._07_datetime;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

public class BeforeJava8Test {

    @Test
    void DateApiTest() {
        Date date = new Date();
        System.out.println("date = " + date);   //Thu Mar 16 14:16:15 KST 2023

        long time = date.getTime();
        System.out.println("time = " + time);   //1678943775380

        date.setTime(time - 1);                                 //1678944050334
        System.out.println("changed time = " + date.getTime()); //1678944050333
    }

    @Test
    void CalendarApiTest() {
        Calendar calendar = new GregorianCalendar(-1000, 2, 15); //month는 0부터 시작하므로 숫자를 바로 쓸 수 없음
        assertThat(calendar.getWeekYear()).isEqualTo(-1000);

        Calendar calendar2 = new GregorianCalendar(2002, Calendar.FEBRUARY, 15);
        System.out.println(calendar2);
    }

    @Test
    void jodaTimeApiTest() {
        DateTime dateTime = new DateTime();
        assertThat(dateTime.getYear()).isEqualTo(2023);
        assertThat(dateTime.getMonthOfYear()).isEqualTo(3);
        assertThat(dateTime.getDayOfMonth()).isEqualTo(16);
    }
}
