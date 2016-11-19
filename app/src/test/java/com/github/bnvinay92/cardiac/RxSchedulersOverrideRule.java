package com.github.bnvinay92.cardiac;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by Vinay on 19/11/16.
 */

public class RxSchedulersOverrideRule implements TestRule {
    private final TestScheduler testScheduler;
    private final Scheduler trampoline;

    public RxSchedulersOverrideRule() {
        this.testScheduler = new TestScheduler();
        this.trampoline = Schedulers.trampoline();
    }

    @Override public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override public void evaluate() throws Throwable {
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
                RxJavaPlugins.setComputationSchedulerHandler(scheduler -> testScheduler);
                RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
                RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
                base.evaluate();
                RxAndroidPlugins.reset();
                RxJavaPlugins.reset();
            }
        };
    }

    public TestScheduler getTestScheduler() {
        return testScheduler;
    }

    public Scheduler getTrampoline() {
        return trampoline;
    }
}

