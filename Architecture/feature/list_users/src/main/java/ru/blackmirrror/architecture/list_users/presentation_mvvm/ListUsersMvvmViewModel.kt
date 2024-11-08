package ru.blackmirrror.architecture.list_users.presentation_mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.list_users.domain.model.User
import ru.blackmirrror.architecture.list_users.domain.usecase.GetUsersUseCase

class ListUsersMvvmViewModel(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _users = MutableStateFlow<ResultState<List<User>>>(ResultState.Loading())
    val users: StateFlow<ResultState<List<User>>> = _users

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase.getUsers().collect {
                _users.value = it
            }
        }
    }
}