package com.tangpj.dateformatutils;


import android.annotation.SuppressLint;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: DateFormatUtils
 * @author create by Tang
 * @date 2017/4/27 下午2:59
 * @description: 时间处理工具类
 * 把时间转换成更容易看懂的格式
 */
public class DateFormatUtils {


    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";

    public static String format(Date date) {
        DateTime dateTime = new DateTime(date);
        return realFormat(DateTime.now(),dateTime);
    }

    public static String format(DateTime dateTime){
        return realFormat(DateTime.now(),dateTime);
    }

    /**
     * @Method: realFormat
     * @author create by Tang
     * @date 2017/5/5 下午3:20
     * @Description: 实现时间转换函数
     */
    @SuppressLint("SimpleDateFormat")
    private static String realFormat(DateTime nowDateTime,DateTime dateTime){
        int seconds = Seconds.secondsBetween(dateTime,nowDateTime).getSeconds();
        if (seconds < 60) {
            return seconds + ONE_SECOND_AGO;
        }

        int minutes = Minutes.minutesBetween(dateTime,nowDateTime).getMinutes();
        if (minutes < 60) {
            return minutes + ONE_MINUTE_AGO;
        }

        int day = nowDateTime.getDayOfYear() - dateTime.getDayOfYear();
        int year = nowDateTime.getYear() - dateTime.getYear();
        if (year < 1 && day < 1) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("今天 HH:mm");
            return dateTime.toString(dateTimeFormatter);
        }

        if (year < 1 && day < 2) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("昨天 HH:mm");
            return dateTime.toString(dateTimeFormatter);
        }
        if (year < 1) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MM月dd日 HH:mm");
            return dateTime.toString(dateTimeFormatter);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy年MM月dd日 HH:mm");
        return dateTime.toString(dateTimeFormatter);
    }


}
