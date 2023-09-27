package com.ardakazanci.gong.data.repository

import com.ardakazanci.gong.core.MemoryCache
import com.ardakazanci.gong.core.domain.DomainResult
import com.ardakazanci.gong.core.domain.MainRepository
import com.ardakazanci.gong.core.getFromMemory
import com.ardakazanci.gong.data.datasource.JsonDataSource
import com.ardakazanci.gong.data.model.PositionsDataModel
import com.ardakazanci.gong.data.model.SatelliteDetailDataModel
import com.ardakazanci.gong.data.model.SatelliteListDataModel
import com.ardakazanci.gong.domain.PositionsDomainModel
import com.ardakazanci.gong.domain.SatelliteDetailDomainModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    override fun getDetail(id: String): Flow<DomainResult<SatelliteDetailDomainModel.SatelliteDetailDomainModelItem>> = channelFlow {
        send(DomainResult.Progress())
        try {
            val detailDataModel = provideGson.fromJson(jsonDataSource.getDetail(),SatelliteDetailDataModel::class.java)
            val filtered = detailDataModel.filter {
                (it.id.toString() == id)
            }.first()
            getFromMemory<SatelliteDetailDataModel.SatelliteDetailDataModelItem>("Cache")?.let {
                takeIf { filtered.equals(it)}?.run { send(DomainResult.Succeed(Result.success(it.mapToDomainModel()))) }
            } ?: run {
                send(DomainResult.Succeed(Result.success(filtered.mapToDomainModel())))
            }
        } catch (e: Exception) {
            send(DomainResult.Succeed(Result.failure(e)))
        }
    }

    override fun getPositions(): Flow<DomainResult<PositionsDomainModel.ListCoreDomainModel>> = channelFlow {
        send(DomainResult.Progress())
        try {
            val positions = provideGson.fromJson(jsonDataSource.getPositions(),PositionsDataModel::class.java)
            positions.list?.forEach {
                delay(3000L)
                send(DomainResult.Succeed(Result.success(it.cast())))
            }
        }catch (e: Exception){
            send(DomainResult.Succeed(Result.failure(e)))
        }
    }
}