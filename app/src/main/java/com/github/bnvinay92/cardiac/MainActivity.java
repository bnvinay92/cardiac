package com.github.bnvinay92.cardiac;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.bnvinay92.cardiac.databinding.ActivityMainBinding;

import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity implements CardiacView {

    ActivityMainBinding binding;

    @Inject CardiacPresenter cardiacPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Cardiac) getApplication()).component().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override protected void onStart() {
        super.onStart();
        cardiacPresenter.attachView(this);
    }

    @Override protected void onStop() {
        super.onStop();
        cardiacPresenter.detachView(isFinishing());
    }

    @Override public Observable<Integer> age() {
        return RxRadioGroup.checkedChanges(binding.age);
    }

    @Override public Observable<Integer> gender() {
        return RxRadioGroup.checkedChanges(binding.gender);
    }

    @Override public Observable<Integer> diabetes() {
        return RxRadioGroup.checkedChanges(binding.diabetes);
    }

    @Override public Observable<Integer> asthma() {
        return RxRadioGroup.checkedChanges(binding.asthma);
    }

    @Override public void showFormIncomplete() {
        binding.status.setText(R.string.incomplete_form);
    }

    @Override public void showResults(HeartRisk risk) {
        String statusText = String.format("%.2f %%, as of %s", risk.risk(), new Date(risk.timestamp()));
        binding.status.setText(statusText);
    }
}
