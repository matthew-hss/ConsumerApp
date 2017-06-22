package com.example.matthew.consumerapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by matthew on 22-06-17.
 */

public class MyApp extends Application{
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
