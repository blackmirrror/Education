package ru.blackmirrror.architecture.list_users.domain

import ru.blackmirrror.architecture.api.model.UserDTO
import ru.blackmirrror.architecture.list_users.domain.model.User

fun UserDTO.toUser(): User {
    return User(
        id = id,
        name = name
    )
}