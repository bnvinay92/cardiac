package com.github.bnvinay92.cardiac;

import javax.inject.Inject;

/**
 * Created by Vinay on 19/11/16.
 */
public class CardiacPresenter {

    CardiacView view;

    @Inject public CardiacPresenter() {
    }

    public void attachView(CardiacView activity) {
        this.view = activity;
    }

    public void detachView(boolean finishing) {

    }
}
