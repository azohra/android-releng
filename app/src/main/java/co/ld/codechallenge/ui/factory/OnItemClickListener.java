/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.ui.factory;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * Handle item click
 *
 * @param <T> Type of data
 */
public interface OnItemClickListener<T> {

    /**
     * Callback when item is clicked
     */
    void onClick(@NonNull T item, @NonNull View view, int position);
}
