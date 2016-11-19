package com.github.bnvinay92.cardiac;

import android.app.Application;

import dagger.Module;

/**
 * Created by Vinay on 19/11/16.
 */
@Module
public class CardiacModule {

    private final Application application;

    public CardiacModule(Application application) {
        this.application = application;
    }
}
