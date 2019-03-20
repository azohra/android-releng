/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.common;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * Root fragment/view to handles common functionality across app.
 */
@SuppressWarnings("all")
public abstract class BaseFragment<T extends ViewModel> extends Fragment {

    protected T mViewModel;

    /**
     * Get the Layout for the user.
     *
     * @return layout to append
     */
    @CheckResult
    @LayoutRes
    protected abstract int getLayout();

    /**
     * Views are ready to be initialized.
     *
     * @param view current view
     */
    protected abstract void initViews(@NonNull View view);

    /**
     * Get {@link ViewModel} Implementor
     *
     * @return Class type of current viewmodel.
     */
    @CheckResult
    @Nullable
    protected abstract Class<T> getViewModel();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Retain state over orientation.
        setRetainInstance(true);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class<T> viewModel = getViewModel();
        if (viewModel != null) {
            // Update View model from default ViewModel Factory.
            mViewModel = ViewModelProviders.of(this).get(viewModel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Create View.
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Views
        initViews(Objects.requireNonNull(getView(), "View is not loaded yet"));
    }
}
