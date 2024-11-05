package ru.blackmirrror.architecture.list_users.presentation_mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.common.PresenterBase
import ru.blackmirrror.architecture.list_users.domain.usecase.GetUsersUseCase

class ListUsersPresenter(
    private val getUsersUseCase: GetUsersUseCase
): PresenterBase<ListUsersContract.View>(), ListUsersContract.Presenter {

    override fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val resultStateFlow = getUsersUseCase.getUsers()

            withContext(Dispatchers.Main) {
                resultStateFlow.collect { state ->
                    when (state) {
                        is ResultState.Loading -> view?.showLoadingState()
                        is ResultState.Error -> view?.showErrorState()
                        is ResultState.Success -> view?.setListUsers(state.data)
                    }
                }
            }
        }
    }

}