package com.joancolmenerodev.github_organization_searcher.base

import android.content.Context

abstract class Page(private val context: Context) {
    abstract fun at()
}