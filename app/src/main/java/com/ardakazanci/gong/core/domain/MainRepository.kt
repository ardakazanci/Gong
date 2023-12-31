package com.ardakazanci.gong.core.domain

import com.ardakazanci.gong.domain.PositionsDomainModel
import com.ardakazanci.gong.domain.SatelliteDetailDomainModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getList(): Flow<DomainResult<SatelliteListDomainModel>>
    fun getDetail(id:String): Flow<DomainResult<SatelliteDetailDomainModel.SatelliteDetailDomainModelItem>>
    fun getPositions(id:String): Flow<DomainResult<PositionsDomainModel.ListCoreDomainModel.Position>>
}