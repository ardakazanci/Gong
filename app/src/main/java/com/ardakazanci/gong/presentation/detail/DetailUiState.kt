package com.ardakazanci.gong.presentation.detail

import androidx.lifecycle.LiveData
import com.ardakazanci.gong.domain.PositionsDomainModel
import com.ardakazanci.gong.domain.SatelliteDetailDomainModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel

sealed class DetailUiState {

    interface ViewModel {
        val uiStateData: LiveData<State>
        fun invokeAction(action: Action)
    }

    sealed class State {
        data class GetDetail(var detail: SatelliteDetailDomainModel.SatelliteDetailDomainModelItem) : State()
        data class GetPositions(var positions: PositionsDomainModel.ListCoreDomainModel.Position) : State()
    }

    sealed class Action {
        data class GetDetail(val id: String) : Action()
        data class GetPosition(val id: String) : Action()
    }
}