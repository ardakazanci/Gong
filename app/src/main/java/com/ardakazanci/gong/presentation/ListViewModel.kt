package com.ardakazanci.gong.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ardakazanci.gong.core.presentation.MotherViewModel
import com.ardakazanci.gong.domain.usecase.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase
) : MotherViewModel(),ListUiState.ViewModel{

    private val _uiStateData = MutableLiveData<ListUiState.State>()
    override val uiStateData: LiveData<ListUiState.State> = _uiStateData

    private fun getList(){
        viewModelScope.launch {
            action(
                getListUseCase.invoke(Unit),
                loading = true
            ) {
                _uiStateData.value = ListUiState.State.GetList(it)
            }
        }

    }

    init {
        getList()
    }
}