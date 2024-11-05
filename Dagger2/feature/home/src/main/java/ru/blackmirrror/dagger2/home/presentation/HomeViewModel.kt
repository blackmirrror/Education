package ru.blackmirrror.dagger2.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.dagger2.home.domain.FirstRepository
import ru.blackmirrror.dagger2.home.domain.SecondRepository
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val firstRepository: FirstRepository,
    private val secondRepository: SecondRepository
) : ViewModel() {

    private var _firstResult: MutableLiveData<Result<Any>> = MutableLiveData()
    val firstResult: LiveData<Result<Any>> = _firstResult

    private var _secondResult: MutableLiveData<Result<Any>> = MutableLiveData()
    val secondResult: LiveData<Result<Any>> = _secondResult

    private var _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    fun onGetFirstResult() {
        viewModelScope.launch {
            try {
                val result = firstRepository.getFirstResult()
                _firstResult.value = Result.success(result)
            } catch (ex: Exception) {
                _firstResult.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }

    fun onGetSecondResult() {
        viewModelScope.launch {
            try {
                val result = secondRepository.getSecondResult()
                _secondResult.value = Result.success(result)
            } catch (ex: Exception) {
                _secondResult.value = Result.failure(ex)
                _error.value = ex
            }
        }
    }
}