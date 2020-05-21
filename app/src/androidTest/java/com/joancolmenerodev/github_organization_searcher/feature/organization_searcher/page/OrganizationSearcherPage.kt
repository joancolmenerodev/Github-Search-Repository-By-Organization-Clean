package com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.page

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.joancolmenerodev.github_organization_searcher.R
import com.joancolmenerodev.github_organization_searcher.base.Page
import com.joancolmenerodev.github_organization_searcher.base.UIElement

class OrganizationSearcherPage(context: Context) : Page(context = context) {

    private val recyclerView = UIElement(withId(R.id.list))
    private val organizationEditText = UIElement(withId(R.id.et_organizationName))
    private val searchOrganization = UIElement(withId(R.id.btn_search_organization))
    private val organizationNotFoundView = UIElement(withId(R.id.image_empty_list))
    private val noInternetView = UIElement(withId(R.id.image_no_internet))

    private val mainLayout =
        UIElement(withId(R.id.linear_search_organization))

    override fun at() {
        mainLayout.isDisplayed()
    }

    fun typeOrganization(organization: String) {
        organizationEditText.type(organization)
    }

    fun searchOrganization() {
        searchOrganization.tap()
    }

    fun showRepositoryInformationOf(position: Int) {
        recyclerView.longClickItemAtPosition(FIRST_POSITION_RECYCLER_VIEW)
    }

    fun isRecyclerViewFilled() {
        recyclerView.clickItemAtPosition(FIRST_POSITION_RECYCLER_VIEW)
    }

    fun isFirstForked() {
        recyclerView.withBackground(FIRST_POSITION_RECYCLER_VIEW, android.R.color.holo_green_dark)
    }

    fun isSecondNotForked() {
        recyclerView.withBackground(SECOND_POSITION_RECYCLER_VIEW, android.R.color.white)
    }

    fun isOrganizationNotFoundLayoutVisible() {
        organizationNotFoundView.isDisplayed()
    }

    fun isNoInternetLayoutVisible() {
        noInternetView.isDisplayed()
    }


    companion object {
        const val FIRST_POSITION_RECYCLER_VIEW = 0
        const val SECOND_POSITION_RECYCLER_VIEW = 1
    }
}