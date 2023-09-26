package com.ardakazanci.gong.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardakazanci.gong.core.domain.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class MotherViewModel : ViewModel() {

    companion object {
        private const val UNKNOWN_ERROR_MESSAGE = "Niye patladı bilmiyom vallahi."
    }

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: SharedFlow<String> = _error

    fun <T> action(result: Flow<DomainResult<T>>, loading: Boolean, action: (T) -> Unit) {
        viewModelScope.launch {
            result.collect { it ->
                when {
                    loading && it is DomainResult.Progress -> {
                        loading(true)
                    }
                    else -> {
                        loading(false)
                        if (it is DomainResult.Succeed) {
                            when {
                                it.data.isSuccess -> {
                                    it.data.getOrNull()?.let {
                                        action.invoke(it)
                                    }
                                }
                                else -> {
                                    it.data.exceptionOrNull() ?: Throwable(
                                        UNKNOWN_ERROR_MESSAGE
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loading(active: Boolean) {
        viewModelScope.launch {
            _loading.value = active
        }
    }
}