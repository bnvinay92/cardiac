package com.github.bnvinay92.cardiac;

import dagger.Component;

/**
 * Created by Vinay on 19/11/16.
 */
@Component(modules = {CardiacModule.class})
public interface CardiacComponent {
    void inject(MainActivity target);
}
