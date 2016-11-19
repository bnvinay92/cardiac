package com.github.bnvinay92.cardiac;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.bnvinay92.cardiac.databinding.ActivityMainBinding;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

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
//        cardiacPresenter.attachView(this);
    }

    @Override protected void onStop() {
        super.onStop();
        cardiacPresenter.detachView(isFinishing());
    }

}
