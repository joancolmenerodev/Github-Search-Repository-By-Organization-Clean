package com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.page

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers
import com.joancolmenerodev.github_organization_searcher.R
import com.joancolmenerodev.github_organization_searcher.base.Page
import com.joancolmenerodev.github_organization_searcher.base.UIElement

class RepositoryDetailBottomSheetPage(context: Context) : Page(context = context) {

    private val bottomSheet = UIElement(ViewMatchers.withId(R.id.constraint_repository_info_bottom_sheet))
    private val repositoryButton = UIElement(ViewMatchers.withId(R.id.tv_bottom_sheet_website_repository))
    private val ownerLoginButton = UIElement(ViewMatchers.withId(R.id.tv_bottom_sheet_owner_profile))


    override fun at() {
        bottomSheet.isDisplayed()
    }

    fun openRepository() {
        repositoryButton.tap()
    }

    fun openOwnerUrl() {
        ownerLoginButton.tap()
    }


}