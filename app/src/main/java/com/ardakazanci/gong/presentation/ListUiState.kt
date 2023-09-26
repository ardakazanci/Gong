package com.ardakazanci.gong.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ardakazanci.gong.domain.SatelliteListDomainModel

sealed class ListUiState {

    interface ViewModel {
        val uiStateData: LiveData<State>
        val filteredItems : MutableLiveData<List<SatelliteListDomainModel.SatelliteListDomainModelItem>>
        val originalItems : MutableLiveData<List<SatelliteListDomainModel.SatelliteListDomainModelItem>>
    }

    sealed class State {
        data class GetList(var list: SatelliteListDomainModel?) : State()
    }
}