package com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.repositorybottomsheet

import android.content.Intent
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joancolmenerodev.github_organization_searcher.base.BaseTest
import com.joancolmenerodev.github_organization_searcher.base.JsonFileToString
import com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.page.OrganizationSearcherPage
import com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.page.RepositoryDetailBottomSheetPage
import com.joancolmenerodev.github_organization_searcher.feature.organization_searcher.searcher.RepositoryByOrganizationSearcherActivityTest
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.RepositoryByOrganizationSearcherActivity
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4ClassRunner::class)
class RepositoryBottomSheetFragmentOpenOwnerUrlTest :
    BaseTest<RepositoryByOrganizationSearcherActivity>() {

    override fun getTestActivity() =
        IntentsTestRule(RepositoryByOrganizationSearcherActivity::class.java, true, false)

    override fun startIntentActivity() {
        launchActivity(null)
    }

    private val organizationSearcherPage = OrganizationSearcherPage(context)
    private val repositoryDetailBottomSheetPage = RepositoryDetailBottomSheetPage(context)

    private val mockResponse =
        JsonFileToString.loadJSONFromAssets(
            context,
            RepositoryByOrganizationSearcherActivityTest.RESPONSE_FILE_NAME
        )

    @Test
    fun openRepositoryUrl() {

        enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(mockResponse)
        )

        at(organizationSearcherPage)

        organizationSearcherPage.typeOrganization(ORGANIZATION_NAME)
        organizationSearcherPage.searchOrganization()
        organizationSearcherPage.showRepositoryInformationOf(FIRST_ITEM)

        at(repositoryDetailBottomSheetPage)
        repositoryDetailBottomSheetPage.openOwnerUrl()
        intended(ownerUrlIntent)
    }


    companion object {
        const val ORGANIZATION_NAME = "xing"
        const val FIRST_ITEM = 0
        private const val OWNER_URL = "https://github.com/xing"

        private val ownerUrlIntent =
            Matchers.allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW), IntentMatchers.hasData(
                    OWNER_URL
                )
            )
    }

}