/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.common;

import android.os.Bundle;

import androidx.annotation.CheckResult;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Root activity responsible for common operation across the app.
 */
public abstract class BaseActivity extends AppCompatActivity {

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
     */
    protected abstract void initViews();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        // Init Views
        initViews();
    }
}
