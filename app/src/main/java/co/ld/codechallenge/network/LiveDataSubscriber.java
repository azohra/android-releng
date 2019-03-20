/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.network;

import java.util.Objects;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import co.ld.codechallenge.data.LiveDataWrap;
import io.reactivex.functions.BiConsumer;

/**
 * Bridge between Rx, LiveData &amp; Error.
 *
 * @param <T>
 */
public class LiveDataSubscriber<T> implements BiConsumer<T, Throwable> {

    @NonNull
    private final LiveDataWrap<T> repoHolder;

    private LiveDataSubscriber(@NonNull LiveDataWrap<T> liveDataHolder) {
        repoHolder = Objects.requireNonNull(liveDataHolder, "Data Wrapper cannot be null");
    }

    /**
     * Create Live Data Subscriber
     *
     * @param liveDataHolder data holder
     * @param <T> type of data
     */
    @NonNull
    @CheckResult
    public static <T> LiveDataSubscriber<T> with(@NonNull LiveDataWrap<T> liveDataHolder) {
        return new LiveDataSubscriber<>(liveDataHolder);
    }

    @Override
    public void accept(@Nullable T data, @Nullable Throwable ex) {
        // Dispatch result wrapped inside Response.
        repoHolder.postValue(Response.createResponse(data, ex));
    }
}
