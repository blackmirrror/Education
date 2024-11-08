package ru.blackmirrror.architecture.chat.presentation_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.domain.ChatRepository
import ru.blackmirrror.architecture.chat.domain.model.Message
import ru.blackmirrror.architecture.chat.domain.usecase.GetMessagesUseCase
import ru.blackmirrror.architecture.chat.domain.usecase.SendMessageUseCase

class ChatMvvmViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val userId: String
): ViewModel() {

    private val _messages = MutableStateFlow<ResultState<List<Message>>>(ResultState.Loading())
    val messages: StateFlow<ResultState<List<Message>>> = _messages

    init {
        getMessages()
    }

    fun getMessages() {
        viewModelScope.launch {
            getMessagesUseCase.getMessages(userId).collect {
                _messages.value = it
            }
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            sendMessageUseCase.sendMessage(userId, message)
        }
    }
}