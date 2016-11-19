package com.github.bnvinay92.cardiac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vinay on 19/11/16.
 */

public class DelightfulOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "cardiac.db";
    public static final int DB_VERSION = 1;

    private static DelightfulOpenHelper instance;

    public static DelightfulOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DelightfulOpenHelper(context);
        }
        return instance;
    }

    private DelightfulOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HeartRisk.CREATE_TABLE);

    }

    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
