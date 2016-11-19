package com.github.bnvinay92.cardiac;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.Component;
import io.reactivex.Completable;

/**
 * Created by Vinay on 19/11/16.
 */
public class SessionRepository {
    @Inject public SessionRepository() {
    }

    public Completable save(HeartRisk risk) {
        return null;
    }
}
