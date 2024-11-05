package ru.blackmirrror.architecture.common

interface MvpPresenter <V : MvpView> {
    fun attachView(view: V)
    fun detachView()
}