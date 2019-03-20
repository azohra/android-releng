/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.network;

import android.util.Pair;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * When Live data is used, error cannot be dispatched directly.
 * Hence live data is wrapped inside response which can deliver response &amp; error
 *
 * @param <T> Type of response.
 */
@SuppressWarnings("ALL")
public class Response<T> extends Pair<T, Throwable> {

    /**
     * Constructor for a Pair.
     *
     * @param first the first object in the Pair
     * @param second the second object in the pair
     */
    private Response(@Nullable T first, @Nullable Throwable second) {
        super(first, second);
    }

    /**
     * Create Response with data
     */
    @NonNull
    @CheckResult
    public static <T> Response<T> createResponse(@Nullable T data) {
        return createResponse(data, null);
    }

    /**
     * Create Response with error
     */
    @NonNull
    @CheckResult
    public static <T> Response<T> createResponse(@Nullable Throwable err) {
        return createResponse(null, err);
    }

    /**
     * Create Response with data &amp; error
     */
    @NonNull
    @CheckResult
    public static <T> Response<T> createResponse(@Nullable T data, @Nullable Throwable err) {
        if (data == null && err == null) {
            throw new IllegalArgumentException("Data & Error are null");
        }
        return new Response<>(data, err);
    }

    /**
     * Get data or {@code null}
     */
    @Nullable
    public T getData() {
        return first;
    }

    /**
     * Get error or {@code null}
     */
    @Nullable
    public Throwable getError() {
        return second;
    }
}
