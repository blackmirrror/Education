package ru.blackmirrror.architecture.chat.domain.model

import ru.blackmirrror.architecture.api.model.ChatDTO
import ru.blackmirrror.architecture.api.model.MessageDTO
import ru.blackmirrror.architecture.api.model.UserDTO

fun UserDTO.toUser(): User {
    return User(
        id = id,
        name = name
    )
}

fun User.toUserDTO(): UserDTO {
    return UserDTO(
        id = id,
        name = name
    )
}

fun ChatDTO.toChat(): Chat {
    return Chat(
        user1 = user1.toUser(),
        user2 = user2.toUser()
    )
}

fun Chat.toChatDTO(): ChatDTO {
    return ChatDTO(
        user1 = user1.toUserDTO(),
        user2 = user2.toUserDTO()
    )
}

fun MessageDTO.toMessage(): Message {
    return Message(
        id = id,
        from = from.toUser(),
        chat = chat.toChat(),
        text = text
    )
}

fun Message.toMessageDTO(): MessageDTO {
    return MessageDTO(
        id = id,
        chat = chat.toChatDTO(),
        from = from.toUserDTO(),
        text = text
    )
}