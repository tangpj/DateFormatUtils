package com.tangpj.dateutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tangpj.dateformatutils.DateFormatUtils;
import com.tangpj.dateformatutils.DateTimeTypeAdapter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final String ISO8061_STR = "\"2017-05-05T06:44:16Z\"";
    private static final String WEIBO_STR1 = "\"Fri May 05 16:00:19 +0800 2017\"";
    private static final String WEIBO_STR2 = "\"Fri May 05 15:54:22 +0800 2017\"";
    private static final String WEIBO_STR3 = "\"Fri May 05 04:22:19 +0800 2017\"";
    private static final String WEIBO_STR4 = "\"Thu May 04 12:00:19 +0800 2017\"";
    private static final String WEIBO_STR5 = "\"Sun Apr 16 06:00:19 +0800 2017\"";
    private static final String WEIBO_STR6 = "\"Thu Nov 05 23:17:19 +0800 2015\"";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
                .create();

        DateTime isoDateTime = gson.fromJson(ISO8061_STR,DateTime.class);
        System.out.println(isoDateTime);
        System.out.println(DateFormatUtils.format(isoDateTime));
        System.out.println("============================");

        DateTimeFormatter weiboFormat =  DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss Z yyyy")
                .withLocale(Locale.US).withZone(DateTimeZone.forID("+08:00"));
        Gson gson1 = new GsonBuilder()
                .registerTypeAdapter(DateTime.class
                        ,new DateTimeTypeAdapter(weiboFormat))
                .create();

        System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR1,DateTime.class)));
        System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR2,DateTime.class)));
        System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR3,DateTime.class)));
        System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR4,DateTime.class)));
        System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR5,DateTime.class)));
        System.out.println(DateFormatUtils.format(gson1.fromJson(WEIBO_STR6,DateTime.class)));

    }


}
