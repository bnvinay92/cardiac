package com.github.bnvinay92.cardiac;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Vinay on 19/11/16.
 */

public class Cardiac extends Application {

    @Override public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
