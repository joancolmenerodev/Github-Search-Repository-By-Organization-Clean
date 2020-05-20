package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation

import android.os.Bundle
import android.view.View
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

    private val layoutIds = arrayListOf(
        R.id.list,
        R.id.image_empty_list,
        R.id.image_no_internet,
        R.id.tv_no_internet_connection
    )

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
        adapter = RepositoriesByOrganizationAdapter {
            val fragment = RepositoryInfoBottomSheetFragment.getInstance(it)
            fragment.show(supportFragmentManager, fragment.tag)
        }
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    override fun showResults(repositories: List<RepositoriesByOrganization>) {
        showCorrectLayout(list)
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
        showCorrectLayout(image_no_internet, tv_no_internet_connection)
    }

    override fun organizationNotFound() {
        showCorrectLayout(image_empty_list)
    }

    private fun showCorrectLayout(vararg viewToChange: View) {
        for (layoutId in layoutIds) {
            for (view in viewToChange) {
                if (view.id == layoutId) {
                    view.visibility = View.VISIBLE
                    break
                } else {
                    findViewById<View>(layoutId).visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewReady(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
