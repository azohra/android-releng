package co.ld.codechallenge.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object Matchers {
    fun withListSize(size: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                val recyclerView = view as RecyclerView
                return recyclerView.childCount == size
            }

            override fun describeTo(description: Description) {
                description.appendText("ListView should have $size items")
            }
        }
    }
}
