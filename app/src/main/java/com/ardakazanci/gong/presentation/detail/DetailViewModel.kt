package com.ardakazanci.gong.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ardakazanci.gong.core.presentation.MotherViewModel
import com.ardakazanci.gong.domain.usecase.GetDetailUseCase
import com.ardakazanci.gong.domain.usecase.GetListUseCase
import com.ardakazanci.gong.domain.usecase.GetPositionsUseCase
import com.ardakazanci.gong.presentation.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
    private val getPositionsUseCase: GetPositionsUseCase
) : MotherViewModel(),DetailUiState.ViewModel{

    private val _uiStateData = MutableLiveData<DetailUiState.State>()
    override val uiStateData: LiveData<DetailUiState.State> = _uiStateData

    override fun invokeAction(action: DetailUiState.Action) {
        when (action) {
            is DetailUiState.Action.GetDetail -> getDetail(action.id)
        }
    }

    private fun getDetail(id: String){
        viewModelScope.launch {
            action(
                getDetailUseCase.invoke(id),
                loading = true
            ) {
                _uiStateData.value = DetailUiState.State.GetDetail(it)
            }
        }
    }

    private fun getPositions(){
        viewModelScope.launch {
            action(
                getPositionsUseCase.invoke(Unit),
                loading = true
            ) {
                _uiStateData.value = DetailUiState.State.GetPositions(it)
            }
        }
    }

    init {
        getPositions()
    }

}