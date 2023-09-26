package com.ardakazanci.gong.data.repository

import com.ardakazanci.gong.core.domain.DomainResult
import com.ardakazanci.gong.core.domain.MainRepository
import com.ardakazanci.gong.data.datasource.JsonDataSource
import com.ardakazanci.gong.data.model.SatelliteListDataModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val jsonDataSource: JsonDataSource,
    private val provideGson: Gson
) : MainRepository {
    override fun getList(): Flow<DomainResult<SatelliteListDomainModel>> = channelFlow {
        send(DomainResult.Progress())
        try {
            val listDataModel = provideGson.fromJson(jsonDataSource.getList(),SatelliteListDataModel::class.java)
            send(DomainResult.Succeed(Result.success(listDataModel.cast())))
        } catch (e: Exception) {
            send(DomainResult.Succeed(Result.failure(e)))
        }
    }
}