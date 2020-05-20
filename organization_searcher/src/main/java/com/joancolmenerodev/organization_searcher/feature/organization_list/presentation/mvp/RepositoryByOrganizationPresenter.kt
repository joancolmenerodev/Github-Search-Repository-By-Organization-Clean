package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.mvp

import com.joancolmenerodev.library_base.presentation.AbstractPresenter
import com.joancolmenerodev.library_base.threading.CoroutineDispatcherProvider
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions.RepositoriesByOrganizationExceptions
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.usecase.GetRepositoriesByOrganizationUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryByOrganizationPresenter @Inject constructor(
    private val getRepositoriesByOrganizationUseCase: GetRepositoriesByOrganizationUseCase,
    private val uiContextProviderCoroutine: CoroutineDispatcherProvider
) : AbstractPresenter<RepositoryByOrganizationContract.View>(uiContextProviderCoroutine),
    RepositoryByOrganizationContract.Presenter {

    override fun loadResults(organization: String) {
        launch {
            view?.showProgressBar(isVisible = true)
            withContext(uiContextProviderCoroutine.io()) {
                getRepositoriesByOrganizationUseCase.execute(organization)
            }.fold(
                ::handleFailure,
                ::handleCoinList
            )
            view?.showProgressBar(isVisible = false)
        }
    }

    private fun handleFailure(failure: RepositoriesByOrganizationExceptions) {
        when (failure) {
            is RepositoriesByOrganizationExceptions.ListNotAvailable -> {
                view?.serviceUnavailable()
            }
            is RepositoriesByOrganizationExceptions.OrganizationNotFound -> {
                view?.listNotFound()
            }
        }
    }

    private fun handleCoinList(crypto: List<RepositoriesByOrganization>) {
        if(crypto.isEmpty()) view?.listNotFound() else view?.showResults(crypto)
    }
}