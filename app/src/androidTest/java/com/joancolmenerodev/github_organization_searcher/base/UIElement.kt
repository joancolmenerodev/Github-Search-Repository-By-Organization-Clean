package com.joancolmenerodev.github_organization_searcher.base

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.adapter.RepositoriesByOrganizationAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher


class UIElement(matcher: Matcher<View>) {

    private val element: ViewInteraction = onView(matcher)

    fun clickItemAtPosition(position: Int) {
        element.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    fun longClickItemAtPosition(position: Int){
        element.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                longClick()
            )
        )
    }

    fun withBackground(position: Int, @ColorInt color: Int) {
        element.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        ).apply {
            withBackgroundColor(color)
        }
    }

    fun onLogClick() {
        element.perform(longClick())
    }

    fun isDisplayed() {
        element.check(matches(ViewMatchers.isDisplayed()))
    }

    fun type(text: String) {
        element.perform(click(), clearText(), typeText(text))
    }

    fun tap() {
        element.perform(click())
    }

    private fun withBackgroundColor(@ColorInt color: Int): Matcher<RecyclerView.ViewHolder> {
        return object :
            BoundedMatcher<RecyclerView.ViewHolder, RepositoriesByOrganizationAdapter.RepositoriesByOrganizationHolder>(
                RepositoriesByOrganizationAdapter.RepositoriesByOrganizationHolder::class.java
            ) {
            override fun matchesSafely(item: RepositoriesByOrganizationAdapter.RepositoriesByOrganizationHolder): Boolean {
                return (item.itemView.background as ColorDrawable).color == color
            }

            override fun describeTo(description: Description) {
            }
        }
    }
}