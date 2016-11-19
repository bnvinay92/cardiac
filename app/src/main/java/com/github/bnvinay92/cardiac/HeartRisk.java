package com.github.bnvinay92.cardiac;

import com.google.auto.value.AutoValue;

import java.util.Date;

/**
 * Created by Vinay on 19/11/16.
 */
@AutoValue
public abstract class HeartRisk {

    public abstract double risk();
    public abstract Date date();
    public abstract CardiacForm form();

    public static HeartRisk create(double risk, Date date, CardiacForm form) {
        return new AutoValue_HeartRisk(risk,date,form);
    }
}
