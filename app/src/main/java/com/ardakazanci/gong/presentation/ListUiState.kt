package com.ardakazanci.gong.presentation

import androidx.lifecycle.LiveData
import com.ardakazanci.gong.domain.SatelliteListDomainModel

sealed class ListUiState {

    interface ViewModel {
        val uiStateData: LiveData<State>
    }

    sealed class State {
        data class GetList(var list: SatelliteListDomainModel?) : State()
    }
}