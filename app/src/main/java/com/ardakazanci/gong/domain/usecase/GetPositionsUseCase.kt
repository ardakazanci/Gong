package com.ardakazanci.gong.domain.usecase

import com.ardakazanci.gong.core.domain.BaseUseCase
import com.ardakazanci.gong.core.domain.DomainResult
import com.ardakazanci.gong.core.domain.MainRepository
import com.ardakazanci.gong.domain.PositionsDomainModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor(
    private val repository: MainRepository
) : BaseUseCase<Unit, PositionsDomainModel.ListCoreDomainModel>() {
    override fun invoke(input: Unit): Flow<DomainResult<PositionsDomainModel.ListCoreDomainModel>> = repository.getPositions()
}