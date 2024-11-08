package ru.blackmirrror.architecture.list_users.presentation_mvi

sealed class ListUsersIntent {
    object GetUsers: ListUsersIntent()
}