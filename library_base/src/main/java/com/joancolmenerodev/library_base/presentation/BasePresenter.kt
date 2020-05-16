package com.joancolmenerodev.library_base.presentation

interface BasePresenter<T> {
    fun onViewReady(view: T)
    fun onViewDestroyed()
}