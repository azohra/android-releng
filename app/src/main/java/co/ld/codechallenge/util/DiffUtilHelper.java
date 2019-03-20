/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import co.ld.codechallenge.model.search.Repo;

/**
 * Helper Utility for Recycler View's Data.
 */
public final class DiffUtilHelper {

    @NonNull
    public static final DiffUtil.ItemCallback<Repo> REPO_DIFF = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.getId() == newItem.getId()
                    && oldItem.getOwner().getId() == newItem.getOwner().getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.getDescription() != null &&
                    oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    private DiffUtilHelper() {
        throw new UnsupportedOperationException("Instance should not be created");
    }
}
