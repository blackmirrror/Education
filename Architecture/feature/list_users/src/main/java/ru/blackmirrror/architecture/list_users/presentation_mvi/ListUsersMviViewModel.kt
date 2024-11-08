package ru.blackmirrror.architecture.list_users.presentation_mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.list_users.domain.model.User
import ru.blackmirrror.architecture.list_users.domain.usecase.GetUsersUseCase

class ListUsersMviViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    var users = MutableStateFlow<ResultState<List<User>>>(ResultState.Loading())
        private set

    init {
        getUsers()
    }

    fun onAction(action: ListUsersIntent) {
        when (action) {
            is ListUsersIntent.GetUsers -> getUsers()
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.getUsers().collect {
                users.value = it
            }
        }
    }
}