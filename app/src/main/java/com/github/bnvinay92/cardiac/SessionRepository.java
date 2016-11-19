package com.github.bnvinay92.cardiac;

import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Vinay on 19/11/16.
 */
public class SessionRepository {
    private final SQLiteDatabase db;

    @Inject public SessionRepository(SQLiteDatabase db) {
        this.db = db;
    }

    public Completable save(HeartRisk risk) {
        return Completable.create(source -> {
            db.insert(HeartRiskModel.TABLE_NAME, null, new HeartRisk.Marshal(risk).asContentValues());
        });
    }
}