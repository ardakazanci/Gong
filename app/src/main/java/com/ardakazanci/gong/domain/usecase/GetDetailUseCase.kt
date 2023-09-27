package com.ardakazanci.gong.domain.usecase

import com.ardakazanci.gong.core.domain.BaseUseCase
import com.ardakazanci.gong.core.domain.DomainResult
import com.ardakazanci.gong.core.domain.MainRepository
import com.ardakazanci.gong.domain.SatelliteDetailDomainModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val repository: MainRepository
) : BaseUseCase<String, SatelliteDetailDomainModel.SatelliteDetailDomainModelItem>() {
    override fun invoke(input: String): Flow<DomainResult<SatelliteDetailDomainModel.SatelliteDetailDomainModelItem>> = repository.getDetail(input)
}