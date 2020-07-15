package co.ld.codechallenge.ui

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.ld.codechallenge.R
import co.ld.codechallenge.model.search.Owner
import co.ld.codechallenge.model.search.Repo
import co.ld.codechallenge.util.AppConstant
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GithubDetailFragmentTest {

    @Before
    fun setUp() {
        val repo = Repo()
        val owner = Owner()
        owner.login = "username"

        repo.name = "name"
        repo.fullName = "full name"
        repo.description = "desc"
        repo.url = "google.com"
        repo.owner = owner
        repo.stargazersCount = 1234

        val fragmentArgs = Bundle().apply {
            putParcelable(AppConstant.DATA_ITEM, repo)
        }

        launchFragmentInContainer<GithubDetailFragment>(
                fragmentArgs = fragmentArgs,
                themeResId = R.style.AppTheme
        )
    }

    @Test
    fun onInflate_shouldPopulateFieldsWithRepoBundle() {
        onView(withId(R.id.full_name))
                .check(matches(withText(containsString("name"))))
        onView(withId(R.id.desc))
                .check(matches(withText(containsString("desc"))))
        onView(withId(R.id.url))
                .check(matches(withText(containsString("google.com"))))
        onView(withId(R.id.username))
                .check(matches(withText(containsString("username"))))
        onView(withId(R.id.stars))
                .check(matches(withText(containsString("1234"))))
    }

    @Test
    fun onFABPress_shouldLaunchUrlIntent() {
        Intents.init()
        val expectedIntent = allOf(hasAction(Intent.ACTION_VIEW), hasData("google.com"))
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))

        onView(withId(R.id.fab)).perform(click())

        intended(expectedIntent)
        Intents.release()
    }
}
