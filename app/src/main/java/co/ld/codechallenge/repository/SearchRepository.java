/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.repository;

import java.util.List;
import java.util.Objects;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import co.ld.codechallenge.data.RepositoryManager;
import co.ld.codechallenge.data.factory.Repository;
import co.ld.codechallenge.model.search.Repo;
import co.ld.codechallenge.model.search.RepoSearch;
import co.ld.codechallenge.network.NetworkManager;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Responsible getting data from cache or server when requested.
 */
public class SearchRepository implements Repository<List<Repo>> {

    // Query prefix
    private static final String QUERY_PREFIX = "topic:";
    private String query;

    @Nullable
    @Override
    public List<Repo> getCachedData() {
        return null;
    }

    @NonNull
    @Override
    public Single<List<Repo>> getRequest() {
        // Creates network request to get data from server.
        return NetworkManager.getInstance()
                // Get service
                .getWebservice()
                // Get Api
                .search(QUERY_PREFIX + Objects.requireNonNull(query, "Query cannot be null"))
                // Transform result
                .map(RepoSearch::getRepos);
    }

    @Override
    public void saveData(@NonNull List<Repo> data) throws Exception {
        // For future usage.
    }

    @NonNull
    @CheckResult
    public Single<List<Repo>> getData(@NonNull String query) {
        this.query = Objects.requireNonNull(query, "Query cannot be null").trim();
        // Create request object
        return RepositoryManager.getInstance()
                // Execute repository
                .execute(this)
                // Receive data in main thread
                .observeOn(AndroidSchedulers.mainThread());
    }
}
