package co.ld.codechallenge.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.ld.codechallenge.R
import co.ld.codechallenge.matchers.Matchers.withListSize
import co.ld.codechallenge.network.NetworkManager
import co.ld.codechallenge.util.EspressoIdlingResource
import com.google.gson.GsonBuilder
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions.Companion.childOfViewAtPositionWithMatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class GithubListFragmentTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        EspressoIdlingResource.setDefaultIdlingResource()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
        val httpClient = OkHttpClient.Builder()

        mockWebServer = MockWebServer()
        retrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(getGsonCoverter())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()
        NetworkManager.getInstance().setExecutor(retrofit)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
        mockWebServer.shutdown()
    }

    @Test
    fun onInflate_shouldInflateWithNoRepos() {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(noRepo)

        mockWebServer.enqueue(response)

        launchFragmentInContainer<GithubListFragment>(
                themeResId = R.style.AppTheme
        )

        onView(withId(R.id.repo_list)).check(ViewAssertions.matches(withListSize(0)))
    }

    @Test
    fun onInflate_shouldPopulateListWithReposReturned() {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(multipleRepo)

        mockWebServer.enqueue(response)

        launchFragmentInContainer<GithubListFragment>(
                themeResId = R.style.AppTheme
        )

        onView(withId(R.id.repo_list)).check(matches(withListSize(2)))
    }

    @Test
    fun onListItemPress_shouldLaunchGithubDetailFragment() {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(multipleRepo)

        mockWebServer.enqueue(response)

        launchFragmentInContainer<GithubListFragment>(
                themeResId = R.style.AppTheme
        )

        onView(withId(R.id.repo_list))
                .check(matches(
                        childOfViewAtPositionWithMatcher(
                                childId = R.id.name,
                                position = 0,
                                childMatcher = withText("full name 1"))
                ))

        onView(withId(R.id.repo_list))
                .check(matches(
                        childOfViewAtPositionWithMatcher(
                                childId = R.id.desc,
                                position = 0,
                                childMatcher = withText("description_one"))
                ))

        onView(withId(R.id.repo_list))
                .check(matches(
                        childOfViewAtPositionWithMatcher(
                                childId = R.id.name,
                                position = 1,
                                childMatcher = withText("full name 2"))
                ))

        onView(withId(R.id.repo_list))
                .check(matches(
                        childOfViewAtPositionWithMatcher(
                                childId = R.id.desc,
                                position = 1,
                                childMatcher = withText("description_two"))
                ))
    }

    private fun getGsonCoverter(): GsonConverterFactory {
        return GsonConverterFactory.create(
                GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .setLenient()
                        .create()
        )
    }

    private val noRepo = """{
        "total_count": 0,
        "incomplete_results": false,
        "items": []
    }"""

    private val multipleRepo = """{
        "total_count": 2,
        "incomplete_results": false,
        "items": [
            {
                id: 1,
                name: 'name 1',
                full_name: 'full name 1',
                description: 'description_one',
                html_url: 'url_one.com',
                stargazers_count: 100,
                owner: {
                    id: 101,
                    login: 'login_one',
                    url: 'owner_url_one.com',
                    avatar_url: 'avatar_url_one.com'
                }
            }, {
                id: 2,
                name: 'name 2',
                full_name: 'full name 2',
                description: 'description_two',
                html_url: 'url_two.com',
                stargazers_count: 200,
                owner: {
                    id: 102,
                    login: 'login_two',
                    url: 'owner_url_two.com',
                    avatar_url: 'avatar_url_two.com'
                }
            }]
    }"""
}
