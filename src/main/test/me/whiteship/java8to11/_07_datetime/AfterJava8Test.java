package me.whiteship.java8to11._07_datetime;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

public class AfterJava8Test {

    @Test
    void instant로_기계용_시간_표현하기() {
        System.out.println("Instant.now() = " + Instant.now());
        System.out.println("Instant.now().atZone(ZoneId.of(\"UTC\")) = " + Instant.now().atZone(ZoneId.of("UTC")));

        //Instant.now() = 2023-03-16T05:52:42.740808Z
        //Instant.now().atZone(ZoneId.of("UTC")) = 2023-03-16T05:52:42.753796Z[UTC]
    }

    @Test
    void zonedDateTIme으로_타임존_표현하기() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("zonedDateTime = " + zonedDateTime); //2023-03-16T14:52:15.378961+09:00[Asia/Seoul]

        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(ZoneId.systemDefault());
        System.out.println("zonedDateTime2 = " + zonedDateTime2);   //2023-03-16T14:52:15.383428+09:00[Asia/Seoul]
    }

    @Test
    void localDateTime으로_인류용_시간_표현하기() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime); //localDateTime = 2023-03-16T14:53:32.503884

        LocalDateTime localDateTime1 = LocalDateTime.of(2002, 2, 15, 11, 23, 30);
        System.out.println("localDateTime1 = " + localDateTime1);   //localDateTime1 = 2002-02-15T11:23:30

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime1, ZoneId.of("Asia/Seoul"));
        System.out.println("zonedDateTime = " + zonedDateTime); //zonedDateTime = 2002-02-15T11:23:30+09:00[Asia/Seoul]
    }

    @Test
    void period로_기간표현하기() {
        System.out.println("LocalDate.now() = " + LocalDate.now());

        Period period = Period.between(LocalDate.of(1994, 8, 11), LocalDate.now());
        System.out.println("period = " + period);   //P-28Y-7M-5D
        System.out.println("period.getYears() = " + period.getYears()); //28
        System.out.println("period.getMonths() = " + period.getMonths());   //7

        Period period2 = Period.between(LocalDate.now(), LocalDate.of(2023, 8, 11));
        System.out.println("period2.getYears() = " + period2.getYears());   //0
        System.out.println("period2.getMonths() = " + period2.getMonths()); //4
        System.out.println("period2.getDays() = " + period2.getDays());     //26
        System.out.println("period2.get(ChronoUnit.DAYS) = " + period2.get(ChronoUnit.DAYS));   //26
    }

    @Test
    void duration으로_기간표현하기() {
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration duration = Duration.between(now, plus);

        assertThat(duration.getSeconds()).isEqualTo(10);
    }

    @Test
    void parsing하는법() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("LocalDate.now().format(dateTimeFormatter) = " + LocalDate.now().format(dateTimeFormatter));
        //LocalDate.now().format(dateTimeFormatter) = 2023-03-16

        LocalDate parsed = LocalDate.parse("1998-08-30", dateTimeFormatter);
        System.out.println("parsed = " + parsed);   //parsed = 1998-08-30 (localDate)
    }

    @Test
    void legacyAPI지원() {
        ZoneId pst = TimeZone.getTimeZone("PST").toZoneId();
        System.out.println("pst = " + pst);     //America/Los_Angeles

        TimeZone legacyZoneAPI = TimeZone.getTimeZone(ZoneId.of("Asia/Seoul"));
        System.out.println("legacyZoneAPI = " + legacyZoneAPI);
        //legacyZoneAPI = sun.util.calendar.ZoneInfo[id="Asia/Seoul",offset=32400000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null]

        Instant newInstant = new Date().toInstant();
        System.out.println("newInstant = " + newInstant);   //newInstant = 2023-03-16T07:09:46.767Z

        Date legacyInstant = Date.from(newInstant);
        System.out.println("legacyInstant = " + legacyInstant); //legacyInstant = Thu Mar 16 16:09:46 KST 2023
    }
}
