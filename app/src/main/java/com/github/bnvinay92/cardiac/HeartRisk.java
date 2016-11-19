package com.github.bnvinay92.cardiac;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.Date;


/**
 * Created by Vinay on 19/11/16.
 */
@AutoValue
public abstract class HeartRisk implements HeartRiskModel{
    public static final Factory<HeartRisk> FACTORY = new Factory<>(AutoValue_HeartRisk::new);

    public static final RowMapper<HeartRisk> SELECT_ALL_MAPPER = FACTORY.select_allMapper();
}
