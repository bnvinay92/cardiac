package com.github.bnvinay92.cardiac;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Vinay on 19/11/16.
 */
public class CardiacPresenter {

    private final SessionRepository sessionRepository;
    private final HeartRiskCalculator heartRiskCalculator;

    private Disposable disposable;
    private CardiacView view;

    @Inject public CardiacPresenter(SessionRepository sessionRepository, HeartRiskCalculator heartRiskCalculator) {
        this.sessionRepository = sessionRepository;
        this.heartRiskCalculator = heartRiskCalculator;
    }

    public void attachView(CardiacView activity) {
        this.view = activity;
        disposable = Observable.combineLatest(
                view.gender(),
                view.age(),
                view.diabetes(),
                view.asthma(),
                CardiacForm::create)
                .distinctUntilChanged()
                .doOnNext(form -> {
                    if (!form.isComplete()) view.showFormIncomplete();
                })
                .filter(CardiacForm::isComplete)
                .flatMapSingle(heartRiskCalculator::execute)
                .subscribe(risk -> {
                    sessionRepository.save(risk);
                    view.showResults(risk);
                },
                        throwable -> Timber.d(throwable,throwable.getMessage()));
    }

    public void detachView(boolean finishing) {
        disposable.dispose();
    }
}
