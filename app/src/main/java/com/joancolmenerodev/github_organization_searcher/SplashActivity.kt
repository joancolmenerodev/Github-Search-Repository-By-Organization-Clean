package com.joancolmenerodev.github_organization_searcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.RepositoryByOrganizationSearcherActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, RepositoryByOrganizationSearcherActivity::class.java))
        finish()
    }

}
