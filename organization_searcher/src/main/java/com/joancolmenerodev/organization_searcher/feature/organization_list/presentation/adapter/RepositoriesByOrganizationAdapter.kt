package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joancolmenerodev.organization_searcher.R
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization

class RepositoriesByOrganizationAdapter(private val onItemClick: (repository: RepositoriesByOrganization) -> Boolean) :
    RecyclerView.Adapter<RepositoriesByOrganizationAdapter.RepositoriesByOrganizationHolder>() {

    private var repositoriesList = ArrayList<RepositoriesByOrganization>()

    fun addItems(repositories: List<RepositoriesByOrganization>) {
        repositoriesList.clear()
        repositoriesList.addAll(repositories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesByOrganizationHolder {
        return RepositoriesByOrganizationHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return repositoriesList.size
    }

    override fun onBindViewHolder(holder: RepositoriesByOrganizationHolder, position: Int) {
        holder.bind(repositoriesList[position], onItemClick)
    }

    class RepositoriesByOrganizationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val repositoryName: TextView = itemView.findViewById(R.id.tv_repoName)
        private val repositoryDescription: TextView = itemView.findViewById(R.id.tv_repoDescription)
        private val ownerName: TextView = itemView.findViewById(R.id.tv_ownerName)

        fun bind(
            repository: RepositoriesByOrganization,
            onItemClick: (repository: RepositoriesByOrganization) -> Boolean
        ) {
            repositoryName.text = repository.name
            repositoryDescription.text = repository.description
            ownerName.text = repository.owner_name
            itemView.setOnLongClickListener { onItemClick.invoke(repository) }

        }
    }

}

