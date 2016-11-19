package com.github.bnvinay92.cardiac;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vinay on 19/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CardiacPresenterTest {

    CardiacPresenter presenter;
    @Mock CardiacView view;
    @Mock SessionRepository sessionRepository;
    @Mock HeartRiskCalculator heartRiskCalculator;

    PublishSubject<Integer> gender = PublishSubject.create();
    PublishSubject<Integer> age = PublishSubject.create();
    PublishSubject<Integer> diabetes = PublishSubject.create();
    PublishSubject<Integer> asthma = PublishSubject.create();

    @Rule public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
        presenter = new CardiacPresenter(sessionRepository, heartRiskCalculator);
        when(view.gender()).thenReturn(gender);
        when(view.age()).thenReturn(age);
        when(view.diabetes()).thenReturn(diabetes);
        when(view.asthma()).thenReturn(asthma);
    }

    @Test
    public void whenFormIncompleteThenShowIncomplete() {
        presenter.attachView(view);
        gender.onNext(-1);
        age.onNext(R.id.age_no);
        diabetes.onNext(R.id.diabetes_yes);
        asthma.onNext(R.id.asthma_yes);
        InOrder inOrder = Mockito.inOrder(view);
        verify(heartRiskCalculator, never()).execute(any());
        inOrder.verify(view, times(1)).showFormIncomplete();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void whenFormCompleteThenShowCompleteAndSaveResults() {
        CardiacForm form = CardiacForm.create(R.id.gender_yes, R.id.age_no, R.id.diabetes_yes, R.id.asthma_yes);
        HeartRisk risk = HeartRisk.FACTORY.creator.create(new Date().getTime(), 75);
        when(heartRiskCalculator.execute(form)).thenReturn(Single.just(risk));
        when(sessionRepository.save(risk)).thenReturn(Completable.complete());
        presenter.attachView(view);
        gender.onNext(R.id.gender_yes);
        age.onNext(R.id.age_no);
        diabetes.onNext(R.id.diabetes_yes);
        asthma.onNext(R.id.asthma_yes);
        InOrder inOrder = Mockito.inOrder(view, sessionRepository, heartRiskCalculator);
        inOrder.verify(sessionRepository, times(1)).save(risk);
        inOrder.verify(view, times(1)).showResults(risk);
        inOrder.verifyNoMoreInteractions();
    }

}