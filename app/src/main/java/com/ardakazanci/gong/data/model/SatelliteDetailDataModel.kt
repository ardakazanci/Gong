package com.ardakazanci.gong.data.model


import com.ardakazanci.gong.data.Mapper
import com.ardakazanci.gong.domain.SatelliteDetailDomainModel
import com.google.gson.annotations.SerializedName

class SatelliteDetailDataModel : ArrayList<SatelliteDetailDataModel.SatelliteDetailDataModelItem>(),Mapper<SatelliteDetailDomainModel> {
    data class SatelliteDetailDataModelItem(
        @SerializedName("cost_per_launch")
        val costPerLaunch: Int?,
        @SerializedName("first_flight")
        val firstFlight: String?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("mass")
        val mass: Int?
    ) : Mapper<SatelliteDetailDomainModel.SatelliteDetailDomainModelItem> {
        override fun cast(): SatelliteDetailDomainModel.SatelliteDetailDomainModelItem {
            return SatelliteDetailDomainModel.SatelliteDetailDomainModelItem(
                costPerLaunch ?: 0,
                firstFlight.orEmpty(),
                height ?: 0,
                id ?: 0,
                mass ?: 0
            )
        }

    }

    override fun cast(): SatelliteDetailDomainModel {
        return this.cast()
    }
}