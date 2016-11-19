package com.github.bnvinay92.cardiac;

import io.reactivex.Observable;

/**
 * Created by Vinay on 19/11/16.
 */
public interface CardiacView {
    Observable<Integer> age();
    Observable<Integer> gender();
    Observable<Integer> diabetes();
    Observable<Integer> asthma();
    void showFormIncomplete();
    void showResults(HeartRisk risk);
}
