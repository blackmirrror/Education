package ru.blackmirrror.architecture.api.model

data class MessageDTO (
    val id: String,
    val chat: ChatDTO,
    val from: UserDTO,
    val text: String
)