package com.joancolmenerodev.organization_searcher.feature.organization_list.presentation

fun getColorByForked(forked: Boolean): Int {
    return if (forked) android.R.color.holo_green_dark else android.R.color.white
}