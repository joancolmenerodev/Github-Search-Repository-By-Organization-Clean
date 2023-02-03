package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.joancolmenerodev.organization_searcher.R
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.getColorByForked

class RepositoriesByOrganizationAdapter(private val onItemClick: (repository: RepositoriesByOrganization) -> Unit) :
    RecyclerView.Adapter<RepositoriesByOrganizationAdapter.RepositoriesByOrganizationHolder>() {

    private var repositoriesList = ArrayList<RepositoriesByOrganization>()

    fun addItems(repositories: List<RepositoriesByOrganization>) {
        repositoriesList.clear()
        repositoriesList.addAll(repositories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
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
        private val cardView: CardView = itemView.findViewById(R.id.repository_card)

        fun bind(
            repository: RepositoriesByOrganization,
            onItemClick: (repository: RepositoriesByOrganization) -> Unit
        ) {
            repositoryName.text = repository.name
            repositoryDescription.text = repository.description
            ownerName.text = repository.owner_name
            cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context, getColorByForked(repository.forked)
                )
            )
            itemView.setOnClickListener { onItemClick.invoke(repository); }
        }
    }

}

