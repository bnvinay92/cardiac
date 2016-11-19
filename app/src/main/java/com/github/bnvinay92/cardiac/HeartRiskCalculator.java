package com.github.bnvinay92.cardiac;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by Vinay on 19/11/16.
 */
public class HeartRiskCalculator {
    @Inject public HeartRiskCalculator() {
    }

    public Single<HeartRisk> execute(CardiacForm form) {
        double risk = 0;
        if (form.gender() == R.id.gender_yes) risk += 25;
        if (form.age() == R.id.age_yes) risk += 25;
        if (form.diabetes() == R.id.diabetes_yes) risk += 25;
        if (form.asthma() == R.id.asthma_yes) risk += 25;
        HeartRisk heartRisk = HeartRisk.FACTORY.creator.create(new Date().getTime(), risk);
        Timber.d(heartRisk.toString());
        return Single.just(heartRisk);
    }
}
