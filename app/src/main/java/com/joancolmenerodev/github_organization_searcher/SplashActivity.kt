package com.joancolmenerodev.github_organization_searcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(3000)
        //startActivity(Intent(this, CryptoListActivity::class.java))
        finish()
    }

}
