/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.network.factory;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import co.ld.codechallenge.model.search.RepoSearch;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Skeleton template of RESTful webservices, that are used to communicate with server.
 */
public interface Webservice {

    /**
     * Get current time
     *
     * @return Request Object to make a call, this itself doesn't execute the call.
     */
    @NonNull
    @CheckResult
    @GET("/search/repositories")
    Single<RepoSearch> search(@NonNull @Query("q") String query);
}
