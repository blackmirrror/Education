package ru.blackmirrror.architecture.chat.presentation_mvp

import ru.blackmirrror.architecture.chat.domain.model.Message
import ru.blackmirrror.architecture.common.MvpPresenter
import ru.blackmirrror.architecture.common.MvpView

interface ChatContract {
    interface View : MvpView {
        fun showLoadingState()
        fun showErrorState()
        fun setMessages(messages: List<Message>)
    }

    interface Presenter : MvpPresenter<View> {
        fun getMessages()
        fun sendMessage(message: String)
    }
}