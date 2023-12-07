package com.example.tanorami.base

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tanorami.activity.MainActivity
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    protected fun ViewInteraction.click(): ViewInteraction = perform(ViewActions.click())

    protected fun <T : RecyclerView.ViewHolder> ViewInteraction.clickOnItem(position: Int): ViewInteraction =
        perform(RecyclerViewActions.actionOnItemAtPosition<T>(position, ViewActions.click()))

    protected fun viewIsDisplayed(): ViewAssertion = matches(ViewMatchers.isDisplayed())

    protected fun viewNotIsDisplayed(): ViewAssertion = matches(not(ViewMatchers.isDisplayed()))

    protected fun ViewInteraction.swipeDown(): ViewInteraction = perform(ViewActions.swipeDown())
}