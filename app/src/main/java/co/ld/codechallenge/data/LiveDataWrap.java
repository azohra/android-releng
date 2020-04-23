/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.data;

import androidx.lifecycle.MutableLiveData;
import co.ld.codechallenge.network.Response;

/**
 * This acts as wrapper around Response&lt;T>
 *
 * @param <T> Type of data
 */
public class LiveDataWrap<T> extends MutableLiveData<Response<T>> {
    // Do nothing
}
