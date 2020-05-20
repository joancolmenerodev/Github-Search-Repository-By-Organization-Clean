package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.mvp

import com.joancolmenerodev.library_base.presentation.BasePresenter
import com.joancolmenerodev.library_base.presentation.PresenterView
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization

interface RepositoryByOrganizationContract {

    interface View : PresenterView {
        fun showResults(repositories: List<RepositoriesByOrganization>)
        fun showProgressBar(isVisible: Boolean)
        fun serviceUnavailable()
        fun organizationNotFound()
    }

    interface Presenter : BasePresenter<View> {
        fun loadResults(organization: String)
    }
}