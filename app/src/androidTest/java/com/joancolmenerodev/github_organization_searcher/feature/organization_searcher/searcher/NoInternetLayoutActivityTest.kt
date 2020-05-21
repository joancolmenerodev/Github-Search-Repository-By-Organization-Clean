package com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.searcher

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joancolmenerodev.github_organization_searcher.base.BaseTest
import com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.page.OrganizationSearcherPage
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.RepositoryByOrganizationSearcherActivity
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4ClassRunner::class)
class NoInternetLayoutActivityTest :
    BaseTest<RepositoryByOrganizationSearcherActivity>() {

    override fun getTestActivity() =
        IntentsTestRule(RepositoryByOrganizationSearcherActivity::class.java, true, false)

    override fun startIntentActivity() {
        launchActivity(null)
    }

    private val organizationSearcherPage = OrganizationSearcherPage(context)

    @Test
    fun noInternetLayoutIsVisible() {

        enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT)
        )

        at(organizationSearcherPage)

        organizationSearcherPage.typeOrganization(ORGANIZATION_NAME)
        organizationSearcherPage.searchOrganization()
        organizationSearcherPage.isNoInternetLayoutVisible()
    }


    companion object {
        const val ORGANIZATION_NAME = "xing"
    }

}