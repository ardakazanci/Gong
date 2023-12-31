package com.ardakazanci.gong.data.di

import com.ardakazanci.gong.core.domain.MainRepository
import com.ardakazanci.gong.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindRepositories {
        @Binds
        abstract fun bindMarketRepository(marketRepositoryImpl: MainRepositoryImpl): MainRepository
    }
}