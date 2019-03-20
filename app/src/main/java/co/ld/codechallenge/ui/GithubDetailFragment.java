/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import co.ld.codechallenge.R;
import co.ld.codechallenge.common.BaseFragment;
import co.ld.codechallenge.model.search.Repo;
import co.ld.codechallenge.util.AppConstant;
import co.ld.codechallenge.util.EspressoIdlingResource;

import static androidx.navigation.Navigation.findNavController;

/**
 * Detailed view of selected repository.
 */
public class GithubDetailFragment extends BaseFragment {

    // Selected item
    private Repo mRepo;

    // Views
    private TextView name;
    private TextView desc;
    private TextView url;
    private TextView username;
    private TextView stars;
    private ImageView dp;

    public GithubDetailFragment() {
        // Required public constructor
    }

    @LayoutRes
    @Override
    protected int getLayout() {
        return R.layout.fragment_github_detail;
    }

    @Nullable
    @Override
    protected Class getViewModel() {
        // No ViewModel required, pass null
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve data from previous view
            mRepo = getArguments().getParcelable(AppConstant.DATA_ITEM);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mRepo == null) {
            // It no data is available, return to previous view
            findNavController(getView()).navigateUp();
        } else {
            // Update data.
            setData();
        }
    }

    @Override
    protected void initViews(@NonNull View view) {
        name = view.findViewById(R.id.full_name);
        desc = view.findViewById(R.id.desc);
        url = view.findViewById(R.id.url);
        username = view.findViewById(R.id.username);
        stars = view.findViewById(R.id.stars);
        dp = view.findViewById(R.id.dp);
    }

    /**
     * Update data to views.
     */
    private void setData() {
        EspressoIdlingResource.increment();
        name.setText(getString(R.string.template_full_name, mRepo.getFullName()));
        desc.setText(getString(R.string.template_desc, mRepo.getDescription()));
        url.setText(getString(R.string.template_url, mRepo.getUrl()));
        username.setText(getString(R.string.template_username, mRepo.getOwner().getLogin()));
        stars.setText(getString(R.string.template_star, mRepo.getStargazersCount()));

        // Load image
        Glide.with(this)
                .load(mRepo.getOwner().getAvatarUrl())
                .centerCrop()
                // place holder until image loads
                .placeholder(R.drawable.ic_launcher_background)
                .into(dp);

        EspressoIdlingResource.decrement();
    }
}
