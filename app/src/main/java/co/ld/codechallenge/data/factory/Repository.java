/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.data.factory;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import co.ld.codechallenge.data.RepositoryManager;
import io.reactivex.Single;

/**
 * Repository to be handled by {@link RepositoryManager}
 *
 * @param <T> Data type received from server
 */
public interface Repository<T> {

    /**
     * Get Cached data from storage
     *
     * @return Cached data or {@code null}
     */
    @CheckResult
    @Nullable
    T getCachedData();

    /**
     * Request to be executed if data is not available.
     *
     * @return Request or {@code null} if no request to be made to server.
     */
    @CheckResult
    @NonNull
    Single<T> getRequest();

    /**
     * Save a copy of data to cache
     *
     * @param data data to be saved.
     */
    @WorkerThread
    void saveData(@NonNull T data) throws Exception;
}
