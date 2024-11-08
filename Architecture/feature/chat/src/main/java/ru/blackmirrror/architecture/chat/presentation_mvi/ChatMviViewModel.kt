package ru.blackmirrror.architecture.chat.presentation_mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.domain.model.Message
import ru.blackmirrror.architecture.chat.domain.usecase.GetMessagesUseCase
import ru.blackmirrror.architecture.chat.domain.usecase.SendMessageUseCase

class ChatMviViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val userId: String
): ViewModel() {

    private val _messages = MutableStateFlow<ResultState<List<Message>>>(ResultState.Loading())
    val messages: StateFlow<ResultState<List<Message>>> = _messages

    init {
        getMessages()
    }

    fun onAction(action: ChatIntent) {
        when (action) {
            is ChatIntent.GetMessages -> getMessages()
            is ChatIntent.SendMessage -> sendMessage(action.message)
        }
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessagesUseCase.getMessages(userId).collect {
                _messages.value = it
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            sendMessageUseCase.sendMessage(userId, message)
        }
    }
}