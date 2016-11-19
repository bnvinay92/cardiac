package com.github.bnvinay92.cardiac;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Vinay on 19/11/16.
 */
public class HeartRiskCalculator {
    @Inject public HeartRiskCalculator() {
    }

    public Single<HeartRisk> execute(CardiacForm form) {
        return null;
    }
}
