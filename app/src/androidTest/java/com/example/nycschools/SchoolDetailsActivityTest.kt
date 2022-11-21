package com.example.nycschools

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nycschools.activity.MainActivity
import com.example.nycschools.activity.SchoolDetailsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SchoolDetailsActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(SchoolDetailsActivity::class.java)

    @Test
    fun checkForDisplayedData() {
        onView(withId(R.id.tv_schoolName)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_math)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_reading)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_writing)).check(matches(isDisplayed()))
    }
}