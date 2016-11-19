package com.github.bnvinay92.cardiac;

import com.google.auto.value.AutoValue;

/**
 * Created by Vinay on 19/11/16.
 */
@AutoValue
public abstract class CardiacForm {
    public abstract int gender();
    public abstract int age();
    public abstract int diabetes();
    public abstract int asthma();

    public static CardiacForm create(int gender, int age, int diabetes, int asthma) {
        return new AutoValue_CardiacForm(gender, age, diabetes, asthma);
    }
}
