package com.ardakazanci.gong.domain.usecase

import com.ardakazanci.gong.core.domain.BaseUseCase
import com.ardakazanci.gong.core.domain.DomainResult
import com.ardakazanci.gong.core.domain.MainRepository
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val repository: MainRepository
) : BaseUseCase<Unit, SatelliteListDomainModel>() {
    override fun invoke(input: Unit): Flow<DomainResult<SatelliteListDomainModel>> = repository.getList()
}
