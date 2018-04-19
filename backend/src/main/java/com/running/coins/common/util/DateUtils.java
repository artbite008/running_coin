package com.running.coins.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parse(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
        String nowTime = sdf.format(date);
        Date time = null;
        try {
            time = sdf.parse(nowTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static Date parseForFrontEnd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm dd");
        String nowTime = sdf.format(date);
        Date time = null;
        try {
            time = sdf.parse(nowTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    public static Date parseForFrontEnd1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd dd");
        String nowTime = sdf.format(date);
        Date time = null;
        try {
            time = sdf.parse(nowTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
