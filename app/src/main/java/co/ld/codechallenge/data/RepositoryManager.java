/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.data;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import javax.inject.Singleton;

import co.ld.codechallenge.data.factory.Repository;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Handles core functionality, it is the single source of truth.
 * Gets data from storage, server, saves, handles responses gracefully.
 */
public final class RepositoryManager {

    private static final RepositoryManager INSTANCE = new RepositoryManager();

    private RepositoryManager() {
        // Hidden Empty constructor
    }

    /**
     * Create instance of {@link RepositoryManager}
     *
     * @return Single Shared Instance across application
     */
    public static RepositoryManager getInstance() {
        return INSTANCE;
    }

    /**
     * Executes request to get data.
     * First data is retrieved from storage, upon failure, the request is sent to server
     * to get data, it also adds functionality to get data from both storage &amp; server.
     * This acts as offline first approach.
     *
     * @param repository Repository to look up.
     * @param <T> Type of data from server
     * @return LiveData in which data is updated when received.
     */
    @NonNull
    @CheckResult
    public <T> Single<T> execute(@NonNull Repository<T> repository) {
        Single<T> source = Single.defer(() -> {
            T fromCache = repository.getCachedData();
            if (fromCache != null) {
                return Single.just(fromCache);
            }

            // Executes request and retrieves data from server.
            return repository.getRequest();
        });

        return attachIoThread(source);
    }

    /**
     * Attaches io thread to current Observable
     *
     * @param source Observable to attach
     * @param <T> Type of data from server
     * @return Updated Observable
     */
    @CheckResult
    private <T> Single<T> attachIoThread(@NonNull Single<T> source) {
        return source.subscribeOn(Schedulers.io());
    }
}
