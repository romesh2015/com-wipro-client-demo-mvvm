package com.wipro.assignment.mvvm.about.view
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
// Here i have written activity related test cases so if name convention written correctly then no
// need any function level comments here.
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