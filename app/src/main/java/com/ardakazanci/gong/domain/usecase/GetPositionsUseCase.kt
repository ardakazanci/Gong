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
) : BaseUseCase<String, PositionsDomainModel.ListCoreDomainModel.Position>() {
    override fun invoke(input: String): Flow<DomainResult<PositionsDomainModel.ListCoreDomainModel.Position>> = repository.getPositions(input)
}