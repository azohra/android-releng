/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.viewmodel;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.Objects;

import co.ld.codechallenge.data.LiveDataWrap;
import co.ld.codechallenge.model.search.Repo;
import co.ld.codechallenge.network.LiveDataSubscriber;
import co.ld.codechallenge.network.Response;
import co.ld.codechallenge.repository.SearchRepository;
import io.reactivex.disposables.CompositeDisposable;

@SuppressWarnings("unused")
public class GithubViewModel extends ViewModel {

    @NonNull
    private final SearchRepository repository = new SearchRepository();

    // Create live data holder.
    @NonNull
    private final LiveDataWrap<List<Repo>> liveData = new LiveDataWrap<>();

    @Nullable
    private LifecycleOwner lifecycle;
    @Nullable
    private CompositeDisposable disposables;
    /**
     * Get list of Repositories for the query
     *
     * @param query search query
     * @return LiveData of {@link Repo}
     */
    @CheckResult
    @NonNull
    public LiveData<Response<List<Repo>>> getRepos(@NonNull String query) {
        if (disposables == null) {
            // Create disposables
            disposables = new CompositeDisposable();
        }

        disposables.add(
                // Get data
                repository.getData(query)
                        // Subscribe for Rx result with LiveData
                        .subscribe(LiveDataSubscriber.with(liveData))
        );
        return liveData;
    }

    /**
     * Get list of Repositories for the query
     *
     * @param query search query
     * @return LiveData of {@link Repo}
     */
    @CheckResult
    @NonNull
    public Publisher<Response<List<Repo>>> getRepoRx(@NonNull String query) {
        if (lifecycle == null) {
            throw new IllegalStateException("Call GithubViewModel#with(LifecycleOwner) first");
        }
        return LiveDataReactiveStreams.toPublisher(lifecycle, getRepos(query));
    }

    /**
     * Update current instance with lifecycle owner for further operations
     *
     * @return updated instance.
     */
    @CheckResult
    @NonNull
    public GithubViewModel with(@NonNull LifecycleOwner lifecycle) {
        this.lifecycle = Objects.requireNonNull(lifecycle, "Life Cycle cannot be null");
        return this;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Clean data observers
        if (disposables != null) {
            disposables.clear();
            disposables.dispose();
            disposables = null;
        }
    }
}
