package com.ardakazanci.gong.core.domain

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Input, Output> {
    abstract operator fun invoke(input: Input): Flow<DomainResult<Output>>
}