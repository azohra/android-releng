/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.util;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;
import co.ld.codechallenge.BuildConfig;

/**
 * Global Espresso idling resource
 */
public final class EspressoIdlingResource {

    private static final String RES_NAME = "EspressoIdlingResource";

    private static CountingIdlingResource sIdlingResource;

    private EspressoIdlingResource() {
        throw new UnsupportedOperationException("Instance should not be created");
    }

    /**
     * Start waiting for data
     */
    public static void increment() {
        if (sIdlingResource != null && BuildConfig.DEBUG) {
            sIdlingResource.increment();
        }
    }

    /**
     * Data is received
     */
    public static void decrement() {
        if (sIdlingResource != null && BuildConfig.DEBUG) {
            sIdlingResource.decrement();
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @NonNull
    public static synchronized IdlingResource getIdlingResource() {
        return Objects.requireNonNull(sIdlingResource, "Missing Idling Resource");
    }

    /**
     * Set Custom idling resource
     */
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static synchronized void setIdlingResource(
            @NonNull CountingIdlingResource idlingResource) {
        if (BuildConfig.DEBUG) {
            sIdlingResource = Objects.requireNonNull(idlingResource);
        }
    }

    /**
     * Set default idling resource.
     */
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void setDefaultIdlingResource() {
        setIdlingResource(new CountingIdlingResource(RES_NAME, true));
    }
}
