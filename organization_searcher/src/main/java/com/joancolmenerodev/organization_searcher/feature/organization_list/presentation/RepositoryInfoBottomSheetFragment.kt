package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joancolmenerodev.organization_searcher.R
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import kotlinx.android.synthetic.main.repository_info_bottom_sheet.*


class RepositoryInfoBottomSheetFragment : BottomSheetDialogFragment() {

    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_info_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var ownerWebsite: String? = ""
        var repositoryWebsite: String? = ""
        this.arguments?.let {
            ownerWebsite = it.getString(REPOSITORY_OWNER_URL_KEY)
            repositoryWebsite = it.getString(REPOSITORY_URL_KEY)
            tv_bottom_sheet_repo_name.text = it.getString(REPOSITORY_NAME_KEY)
            tv_bottom_sheet_owner_login.text = it.getString(REPOSITORY_OWNER_NAME_KEY)
            loadImage(it.getString(REPOSITORY_OWNER_AVATAR_KEY))
        }
        tv_bottom_sheet_website_repository.setOnClickListener {
            openWebSite(repositoryWebsite)

        }
        tv_bottom_sheet_owner_profile.setOnClickListener {
            openWebSite(ownerWebsite)
        }
    }

    private fun openWebSite(url: String?) {
        val browserIntent =
            Intent(
                Intent.ACTION_VIEW, Uri.parse(url)
            )
        startActivity(browserIntent)
    }

    private fun loadImage(url: String?) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(iv_bottom_sheet_owner_avatar)
    }

    companion object {
        private const val REPOSITORY_NAME_KEY: String = "REPOSITORY_NAME_KEY"
        private const val REPOSITORY_OWNER_AVATAR_KEY: String = "REPOSITORY_OWNER_AVATAR_KEY"
        private const val REPOSITORY_OWNER_NAME_KEY: String = "REPOSITORY_OWNER_NAME_KEY"
        private const val REPOSITORY_OWNER_URL_KEY: String = "REPOSITORY_OWNER_URL_KEY"
        private const val REPOSITORY_URL_KEY: String = "REPOSITORY_URL_KEY"

        @JvmStatic
        fun getInstance(
            repository: RepositoriesByOrganization
        ): RepositoryInfoBottomSheetFragment {

            val bundle = Bundle()
            with(bundle) {
                putString(REPOSITORY_NAME_KEY, repository.name)
                putString(REPOSITORY_OWNER_AVATAR_KEY, repository.owner_avatar)
                putString(REPOSITORY_OWNER_NAME_KEY, repository.owner_name)
                putString(REPOSITORY_OWNER_URL_KEY, repository.owner_url)
                putString(REPOSITORY_URL_KEY, repository.url)
            }
            val fragment = RepositoryInfoBottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

}
