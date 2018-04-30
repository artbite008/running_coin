package com.running.coins.common.util;

import org.apache.tomcat.jni.Local;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * ThisLocalizedWeekTest
 *
 * @author guxiang
 * @date 2018/4/29
 */
public class ThisLocalizedWeekTest {

    @Test
    public void getFirstDay() throws ParseException {
        ThisLocalizedWeek thisLocalizedWeek = new ThisLocalizedWeek(Locale.CHINA);
        Date firstDay = thisLocalizedWeek.getFirstDay();
        Date firstday1 = DateUtils.parse(firstDay);
        System.out.println(  DateUtils.parseForFrontEnd1(firstday1));

        Date firstDay2 = thisLocalizedWeek.getLastDay();
        System.out.println(DateUtils.parseForFrontEnd1(firstDay2));

    }

    @Test
    public void getLastDay() {
        LocalDate localDate = LocalDate.now();
        localDate.getDayOfWeek();
    }
}