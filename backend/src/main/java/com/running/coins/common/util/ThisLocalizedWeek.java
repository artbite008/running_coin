package com.running.coins.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

public class ThisLocalizedWeek {// Try and always specify the time zone you're working with
    // Try and always specify the time zone you're working with
    private final static ZoneId TZ = ZoneId.of("Asia/Harbin");

    private final Locale locale;
    private final DayOfWeek firstDayOfWeek;
    private final DayOfWeek lastDayOfWeek;

    public ThisLocalizedWeek(final Locale locale) {
        this.locale = locale;
        this.firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
        this.lastDayOfWeek = DayOfWeek.of(((this.firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
    }

    public Date getFirstDay() {
        LocalDate localDate = LocalDate.now(TZ).with(TemporalAdjusters.previousOrSame(this.firstDayOfWeek));
        Date date = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
        return DateUtils.parse(date);
    }

    public Date getLastDay() {
        LocalDate localDate = LocalDate.now(TZ).with(TemporalAdjusters.nextOrSame(this.lastDayOfWeek));
        Date date = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
        return DateUtils.parse(date);
    }

    /**
     * @param date 2018-04-18 11:11:11
     * @return
     */

    public Date getFirstDay(String date) {
        String[] strs = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]), Integer.valueOf(strs[2].substring(0, 2))).with(TemporalAdjusters.previousOrSame(this.firstDayOfWeek));
        Date dateReturn = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
        return DateUtils.parse(dateReturn);
    }

    public Date getLastDay(String date) {
        String[] strs = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.valueOf(strs[0]), Integer.valueOf(strs[1]), Integer.valueOf(strs[2].substring(0, 2))).with(TemporalAdjusters.nextOrSame(this.lastDayOfWeek));
        Date dateReturn = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
        return DateUtils.parse(dateReturn);
    }


    @Override
    public String toString() {
        return String.format("The %s week starts on %s and ends on %s",
                this.locale.getDisplayName(),
                this.firstDayOfWeek,
                this.lastDayOfWeek);
    }
}
