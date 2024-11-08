package ru.blackmirrror.architecture.common

abstract class PresenterBase<V: MvpView> : MvpPresenter<V> {
    var view: V? = null
        private set

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}