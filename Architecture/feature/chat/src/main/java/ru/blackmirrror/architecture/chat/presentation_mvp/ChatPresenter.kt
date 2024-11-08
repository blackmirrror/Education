package ru.blackmirrror.architecture.chat.presentation_mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.domain.usecase.GetMessagesUseCase
import ru.blackmirrror.architecture.chat.domain.usecase.SendMessageUseCase
import ru.blackmirrror.architecture.common.PresenterBase

class ChatPresenter(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val userId: String
): PresenterBase<ChatContract.View>(), ChatContract.Presenter {

    override fun getMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            val messages = getMessagesUseCase.getMessages(userId)
            withContext(Dispatchers.Main) {
                messages.collect { state ->
                    when (state) {
                        is ResultState.Success -> view?.setMessages(state.data)
                        is ResultState.Loading -> view?.showLoadingState()
                        is ResultState.Error -> view?.showErrorState()
                    }
                }
            }
        }
    }

    override fun sendMessage(message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val messages = sendMessageUseCase.sendMessage(userId, message)
            withContext(Dispatchers.Main) {
                messages.collect { state ->
                    when (state) {
                        is ResultState.Success -> Unit
                        is ResultState.Loading -> view?.showLoadingState()
                        is ResultState.Error -> view?.showErrorState()
                    }
                }
            }
        }
    }

}