package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.joancolmenerodev.organization_searcher.R
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.adapter.RepositoriesByOrganizationAdapter
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.mvp.RepositoryByOrganizationContract
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_repository_by_organization_searcher.*
import javax.inject.Inject

class RepositoryByOrganizationSearcherActivity : AppCompatActivity(),
    RepositoryByOrganizationContract.View {

    private lateinit var adapter: RepositoriesByOrganizationAdapter

    @Inject
    lateinit var presenter: RepositoryByOrganizationContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_by_organization_searcher)
        inject()
        setupRecyclerView()
        presenter.onViewReady(this)

        btn_search_organization.setOnClickListener {
            presenter.loadResults(et_organizationName.text.toString())
        }

    }

    private fun inject() {
        AndroidInjection.inject(this)
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = LinearLayoutManager(this)
        adapter = RepositoriesByOrganizationAdapter {
            val fragment = RepositoryInfoBottomSheetFragment.getInstance(it)
            fragment.show(supportFragmentManager, fragment.tag);
        }
        list.layoutManager = gridLayoutManager
        list.adapter = adapter
    }

    override fun showResults(repositories: List<RepositoriesByOrganization>) {
        tv_emtpyList.visibility = View.GONE
        adapter.addItems(repositories)
    }

    override fun showProgressBar(isVisible: Boolean) {
        progress_bar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun serviceUnavailable() {
        Toast.makeText(this, "Service unavailable", Toast.LENGTH_SHORT).show()
    }

    override fun listNotFound() {
        tv_emtpyList.visibility = View.VISIBLE
    }
}
