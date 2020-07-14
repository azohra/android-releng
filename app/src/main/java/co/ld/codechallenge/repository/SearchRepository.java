/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.repository;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.ld.DaggerAppComponent;
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
@Singleton
public class SearchRepository implements Repository<List<Repo>> {

    @Inject
    public NetworkManager networkManager;

    @Inject
    public RepositoryManager repositoryManager;

    // Query prefix
    private static final String QUERY_PREFIX = "topic:";
    private String query;

    public SearchRepository() {
        DaggerAppComponent.create().searchRepository(this);
    }

    @Nullable
    @Override
    public List<Repo> getCachedData() {
        return null;
    }

    @NonNull
    @Override //Untestable
    public Single<List<Repo>> getRequest() { // <- RepositoryManager.execute <- this.getData
        // Creates network request to get data from server.
        return networkManager
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
    public Single<List<Repo>> getData(@NonNull String query) { //GithubViewModel
        this.query = Objects.requireNonNull(query, "Query cannot be null").trim();
        // Create request object
        return repositoryManager
                // Execute repository
                .execute(this)
                // Receive data in main thread
                .observeOn(AndroidSchedulers.mainThread());
    }
}
