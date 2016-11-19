package com.github.bnvinay92.cardiac;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vinay on 19/11/16.
 */
@Module
public class CardiacModule {

    private final Application application;

    public CardiacModule(Application application) {
        this.application = application;
    }

    @Provides public Context context() {
        return application;
    }

    @Provides public DelightfulOpenHelper delightfulOpenHelper(Context context) {
        return DelightfulOpenHelper.getInstance(context);
    }

    @Provides public SQLiteDatabase sqLiteDatabase(DelightfulOpenHelper delightfulOpenHelper) {
        return delightfulOpenHelper.getWritableDatabase();
    }
}
