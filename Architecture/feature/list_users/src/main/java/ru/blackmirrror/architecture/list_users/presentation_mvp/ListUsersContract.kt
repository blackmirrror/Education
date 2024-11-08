package ru.blackmirrror.architecture.list_users.presentation_mvp

import ru.blackmirrror.architecture.common.MvpPresenter
import ru.blackmirrror.architecture.common.MvpView
import ru.blackmirrror.architecture.list_users.domain.model.User

interface ListUsersContract {
    interface View : MvpView {
        fun showLoadingState()
        fun showErrorState()
        fun setListUsers(users: List<User>)
    }

    interface Presenter : MvpPresenter<View> {
        fun getUsers()
    }
}