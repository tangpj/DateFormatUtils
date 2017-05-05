package com.tangpj.dateutil;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * @className: MyApp
 * @author create by Tang
 * @date 2017/5/5 上午10:10
 * @description:
 */
public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);

    }
}
