package com.wipro.assignment.mvvm.about.view.fragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.wipro.assignment.mvvm.R
import com.wipro.assignment.mvvm.about.view.AboutActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
// Here we have tested Fragment ui related cased and name convention already show the purpose of
 // function no need to write class function level comments here.
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
        onView(withId(R.id.fragment_about_main_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun isFragmentListToolbarExist() {
        onView(withId(R.id.fragment_about_toolbar_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun isFragmentRecylerViewExist() {
        onView(withId(R.id.fragment_about_recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }
    @Test
    fun isFragmentSwipeToRefreshExist() {
        onView(withId(R.id.fragment_about_swipe_fresh))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }
    @After
    fun tearDown() {
    }
}