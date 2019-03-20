/*
 * Copyright (c) 2019 Yoganandh Durvasulu. All rights reserved.
 */

package co.ld.codechallenge.core;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;

import androidx.annotation.CallSuper;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import co.ld.codechallenge.network.NetworkManager;
import co.ld.codechallenge.util.EspressoIdlingResource;
import co.ld.codechallenge.util.TestHelper;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;

@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public abstract class BaseTest {

    protected static MockWebServer server;
    protected TestHelper testHelper;

    @BeforeClass
    public static void setUpAll() throws IOException {
        EspressoIdlingResource.setDefaultIdlingResource();

        server = new MockWebServer();
        server.start();
        Retrofit executor = new TestHelper(null).getExecutor(server.url("/").toString());
        NetworkManager.getInstance().setExecutor(executor);
    }

    @CallSuper
    @Before
    public void setUp() {
        testHelper = new TestHelper(this);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @CallSuper
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}
