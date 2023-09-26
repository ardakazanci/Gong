package com.ardakazanci.gong.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ardakazanci.gong.core.presentation.MotherViewModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel
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

    private val _filteredItems = MutableLiveData<List<SatelliteListDomainModel.SatelliteListDomainModelItem>>()
    override val filteredItems: MutableLiveData<List<SatelliteListDomainModel.SatelliteListDomainModelItem>> = _filteredItems

    private val _originalItems = MutableLiveData<List<SatelliteListDomainModel.SatelliteListDomainModelItem>>()
    override val originalItems: MutableLiveData<List<SatelliteListDomainModel.SatelliteListDomainModelItem>> = _originalItems

    private fun getList(){
        viewModelScope.launch {
            action(
                getListUseCase.invoke(Unit),
                loading = true
            ) {
                filteredItems.value = it
                _originalItems.value = it
                _uiStateData.value = ListUiState.State.GetList(it)
            }
        }
    }

    init {
        getList()
    }
}