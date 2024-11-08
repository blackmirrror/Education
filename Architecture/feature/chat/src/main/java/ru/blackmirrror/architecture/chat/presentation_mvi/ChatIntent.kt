package ru.blackmirrror.architecture.chat.presentation_mvi

sealed class ChatIntent {
    object GetMessages: ChatIntent()
    class SendMessage(val message: String): ChatIntent()
}