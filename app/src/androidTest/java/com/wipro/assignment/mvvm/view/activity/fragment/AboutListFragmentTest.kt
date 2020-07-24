package com.wipro.assignment.mvvm.view.activity.fragment
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.view.activity.AboutActivity
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@Suppress("DEPRECATION")
@SuppressWarnings( "deprecation" )
@RunWith(AndroidJUnit4::class)
class AboutListFragmentTest {
    @get:Rule
    val activityRule = ActivityTestRule(AboutActivity::class.java)
    @Before
    fun setUp() {
        activityRule.getActivity().supportFragmentManager.beginTransaction();
    }
    @Test
    fun isFragmentListMainResourceFileExist() {
        onView(withId(R.id.list_fragment_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun isFragmentListToolbarExist() {
        onView(withId(R.id.toolbar_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun isFragmentRecylerViewExist() {
        onView(withId(R.id.recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }
    @Test
    fun isFragmentSwipeToRefreshExist() {
        onView(withId(R.id.swiperefresh))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }
    @After
    fun tearDown() {
    }
}