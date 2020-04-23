/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.network;

import com.google.gson.GsonBuilder;

import androidx.annotation.CheckResult;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import co.ld.codechallenge.network.factory.Webservice;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Handles all network operation.
 * This internally handles token authentication.
 * When token is expired, pull all pending calls in queue &amp;
 * executes token request upon new token received, executes pending request with new token.
 * In case of token failure, all pending request is dispatched with error.
 */
public final class NetworkManager {

    private static final NetworkManager INSTANCE = new NetworkManager();

    private static final String BASE_URL = "https://api.github.com";

    /* This is usually injected from application class for ease of testing. */
    private Retrofit mRetrofit;

    private NetworkManager() {
        // Hidden Empty constructor
    }

    /**
     * Create instance of {@link NetworkManager}
     *
     * @return Single Shared Instance across application
     */
    public static NetworkManager getInstance() {
        return INSTANCE;
    }

    /**
     * Creates <b>Thread safe</b> single copy of network client, with on demand creation.
     *
     * @return Single Shared Instance across application
     */
    @WorkerThread
    @CheckResult
    private Retrofit getExecutor() {
        if (mRetrofit == null) {
            synchronized (this) {
                if (mRetrofit == null) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(getGsonCoverter())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(httpClient.build())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public void setExecutor(Retrofit executor) {
        synchronized (this) {
            mRetrofit = executor;
        }
    }

    /**
     * Creates custom copy of {@link com.google.gson.Gson}
     * Avoid creating multiple copies of Gson, as it is heavy.
     *
     * @return copy of Gson.
     */
    @CheckResult
    private GsonConverterFactory getGsonCoverter() {
        return GsonConverterFactory.create(
                new GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .setLenient()
                        .create()
        );
    }

    /**
     * Creates new copy of Webservice to make RESTful API calls.
     *
     * @return Copy of {@link Webservice}
     */
    @CheckResult
    public Webservice getWebservice() {
        return getExecutor().create(Webservice.class);
    }
}
