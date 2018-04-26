package com.running.coins.common.util;

import javax.xml.crypto.Data;
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

    public static String parseForFrontEnd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm E");
        String nowTime = sdf.format(date);
        return nowTime;
    }
    public static String parseForFrontEnd1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");
        String nowTime = sdf.format(date);
        return nowTime;
    }

    public static Date[] parseToDayRange(Date date){
        Date[] dates = new Date[2];
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd");
        String nowDay = sdf.format(date);
        date.setDate(date.getDay()-1);
        String preDay = sdf.format(date);

        preDay += "12:00:00";
        return  null;
    }
}
