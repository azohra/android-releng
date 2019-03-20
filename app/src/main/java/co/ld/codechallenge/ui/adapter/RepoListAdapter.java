/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import co.ld.codechallenge.R;
import co.ld.codechallenge.model.search.Repo;
import co.ld.codechallenge.ui.factory.OnItemClickListener;
import co.ld.codechallenge.util.DiffUtilHelper;

/**
 * Recycler Adapter for displaying list of results.
 */
public class RepoListAdapter extends ListAdapter<Repo, RepoListAdapter.RepoViewHolder> {

    // Image renderer
    @NonNull
    private final RequestManager mGlide;
    // Event listener
    @Nullable
    private OnItemClickListener<Repo> mItemClickListener;

    public RepoListAdapter(@NonNull RequestManager glide) {
        // Pass diff helper.
        super(DiffUtilHelper.REPO_DIFF);
        mGlide = Objects.requireNonNull(glide, "Glide is null");
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                // Update item view
                .inflate(R.layout.github_item, parent, false);

        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        // Get item
        Repo repo = getItem(position);

        // Update views
        holder.name.setText(repo.getFullName());
        holder.desc.setText(repo.getDescription());

        // Load image
        mGlide.load(repo.getOwner().getAvatarUrl())
                .centerCrop()
                // place holder until image loads
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.dp);
    }

    public void setOnItemClickLister(@Nullable OnItemClickListener<Repo> listener) {
        mItemClickListener = listener;
    }

    /**
     * View Holder pattern, used by recycler view.
     */
    class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView name;
        private final TextView desc;
        private final ImageView dp;

        RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            dp = itemView.findViewById(R.id.dp);

            // Add event listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int selectedPos = getAdapterPosition();
            if (selectedPos == RecyclerView.NO_POSITION) {
                return;
            }

            if (mItemClickListener != null) {
                // Get item
                Repo repo = getItem(selectedPos);

                // Pass on to root level event listener
                mItemClickListener.onClick(repo, view, selectedPos);
            }
        }
    }
}
