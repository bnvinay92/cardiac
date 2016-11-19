package com.github.bnvinay92.cardiac;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;

import io.reactivex.Observable;
import io.reactivex.android.MainThreadDisposable;

/**
 * Created by Vinay on 19/11/16.
 */

public final class RxRadioGroup {
    /**
     * Create an observable of the checked view ID changes in {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p>
     * <em>Note:</em> A value will be emitted immediately on subscribe.
     */
    @CheckResult @NonNull
    public static Observable<Integer> checkedChanges(@NonNull RadioGroup view) {
        return Observable.<Integer>create(subscriber -> {
            MainThreadDisposable.verifyMainThread();
            RadioGroup.OnCheckedChangeListener listener = (group, checkedId) -> {
                if (!subscriber.isDisposed()) {
                    subscriber.onNext(checkedId);
                }
            };
            view.setOnCheckedChangeListener(listener);

            subscriber.setDisposable(new MainThreadDisposable() {
                @Override protected void onDispose() {
                    view.setOnCheckedChangeListener(null);
                }
            });

            subscriber.onNext(view.getCheckedRadioButtonId());
        })
                .distinctUntilChanged();
    }

    private RxRadioGroup() {
        throw new AssertionError("No instances.");
    }
}

