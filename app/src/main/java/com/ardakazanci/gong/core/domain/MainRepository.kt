package com.ardakazanci.gong.core.domain

import com.ardakazanci.gong.domain.SatelliteListDomainModel
import kotlinx.coroutines.flow.Flow

interface MarketRepository {
    fun getList(): Flow<DomainResult<SatelliteListDomainModel>>
}