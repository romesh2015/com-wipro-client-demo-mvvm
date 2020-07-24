package com.wipro.assignment.mvvm.view.activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.whenResumed
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wipro.assignment.mvvm.R
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
@Suppress("DEPRECATION")
@SuppressWarnings( "deprecation" )
@RunWith(AndroidJUnit4::class)
@LargeTest
class AboutActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(AboutActivity::class.java)
    @Before
    fun setUp() {
    }
    @Test
    fun isActivityMainResourceFileExist() {
        Espresso.onView(withId(R.id.activity_about_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun isFrameLayoutViewExist() {
        Espresso.onView(withId(R.id.fragment_container_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun onCreateCalled() {
        val scenario = launchActivity<AboutActivity>()
        scenario.onActivity { activity ->
            if(activity.hasWindowFocus()){
                return@onActivity
            }
        }
    }
    @After
    fun tearDown() {
    }

}