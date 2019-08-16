package com.allqj.virtual_number_administrate.util.dateOperation;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DateOperation {

    private static String format = "yyyyMMddHHmmss";

    //private static String timeZone = "Asia/Shanghai";
    //private static String timeZone = "America/Chicago";

    public static Date newDate() {
        return new Date();
    }

    public static String newDateString() {
        String resultStr = newDateString(format);
        return resultStr;
    }

    public static String newDateString(String format) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(format);
        String result = simpleDateFormat.format(newDate());
        return result;
    }

    public static Date stringDate(String strDate, String format) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(strDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String dateString(Date date) {
        String resultStr = dateString(date, format);
        return resultStr;
    }

    public static String dateString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(format);
        String result = simpleDateFormat.format(date);
        return result;
    }

    private static SimpleDateFormat getSimpleDateFormat(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat;
    }
}
