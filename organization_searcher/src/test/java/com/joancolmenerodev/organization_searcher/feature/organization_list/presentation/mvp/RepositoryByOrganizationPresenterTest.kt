package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.mvp

import com.joancolmenerodev.library_base.Either
import com.joancolmenerodev.library_base.threading.TestCoroutineDispatcherProvider
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions.RepositoriesByOrganizationExceptions
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.usecase.GetRepositoriesByOrganizationUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test


class RepositoryByOrganizationPresenterTest {

    private lateinit var presenter: RepositoryByOrganizationContract.Presenter

    @RelaxedMockK
    private lateinit var mockView: RepositoryByOrganizationContract.View

    @MockK
    private lateinit var mockUseCase: GetRepositoriesByOrganizationUseCase

    private val testCoroutineDispatcher = TestCoroutineDispatcherProvider()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = RepositoryByOrganizationPresenter(mockUseCase, testCoroutineDispatcher)
        presenter.onViewReady(mockView)
    }

    @After
    fun tearDown() {
        presenter.onViewDestroyed()
        unmockkAll()
    }

    @Test
    fun `given user searches for an organization then it returns a list of repositories then it displays the repositories`() {

        //ASSIGN
        coEvery { mockUseCase.execute(any()) } answers { Either.Right(repositoriesByOrganization) }

        //ACT
        presenter.loadResults(ORGANIZATION_NAME)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.showResults(repositoriesByOrganization)
            mockView.showProgressBar(false)
        }

    }

    @Test
    fun `given user searches for an organization then it returns an empty list of repositories then it shows listNotFound layout`() {

        //ASSIGN
        coEvery { mockUseCase.execute(any()) } answers { Either.Right(emptyList()) }

        //ACT
        presenter.loadResults(ORGANIZATION_NAME)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.organizationNotFound()
            mockView.showProgressBar(false)
        }

    }

    @Test
    fun `given user searches for an organization then it fails because there's no internet connection then it shows the serviceUnavailable layout`() {

        //ASSIGN
        coEvery { mockUseCase.execute(any()) } answers {
            Either.Left(
                RepositoriesByOrganizationExceptions.ListNotAvailable
            )
        }

        //ACT
        presenter.loadResults(ORGANIZATION_NAME)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.serviceUnavailable()
            mockView.showProgressBar(false)
        }

    }

    @Test
    fun `given user searches for an organization then it fails because there's no organization with that name then it shows the listNotFound layout`() {

        //ASSIGN
        coEvery { mockUseCase.execute(any()) } answers {
            Either.Left(
                RepositoriesByOrganizationExceptions.OrganizationNotFound
            )
        }

        //ACT
        presenter.loadResults(ORGANIZATION_NAME)

        //ASSERT
        coVerify(exactly = 1) {
            mockView.showProgressBar(true)
            mockView.organizationNotFound()
            mockView.showProgressBar(false)
        }

    }

    companion object {
        private const val ORGANIZATION_NAME = "XING"
        val repositoriesByOrganization = listOf(
            RepositoriesByOrganization(
                id = 1,
                name = "Repository 1",
                description = "Description of repo 1",
                url = "https://wwww.repo1.com",
                forked = true,
                owner_name = "Joan",
                owner_avatar = "https://www.avatar_of_joan.com",
                owner_url = "https://www.joan.com"
            ),
            RepositoriesByOrganization(
                id = 2,
                name = "Repository 2",
                description = "Description of repo 2",
                url = "https://wwww.repo2.com",
                forked = false,
                owner_name = "Gustavo",
                owner_avatar = "https://www.avatar_of_gustavo.com",
                owner_url = "https://www.gustavo.com"
            )
        )

    }

}