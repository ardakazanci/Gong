package com.ardakazanci.gong.data.model


import com.ardakazanci.gong.data.Mapper
import com.ardakazanci.gong.domain.SatelliteDetailDomainModel
import com.ardakazanci.gong.domain.SatelliteListDomainModel
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
    )  {
        fun mapToDomainModel(): SatelliteDetailDomainModel.SatelliteDetailDomainModelItem {
            return SatelliteDetailDomainModel.SatelliteDetailDomainModelItem(
                id = this.id ?: 0,
                costPerLaunch = this.costPerLaunch ?: 0,
                firstFlight = this.firstFlight.orEmpty(),
                height = this.height ?: 0,
                mass = this.mass ?: 0

            )
        }
    }

    override fun cast() : SatelliteDetailDomainModel {
        val domainModelItems = this.map { it.mapToDomainModel() }
        return SatelliteDetailDomainModel().apply { addAll(domainModelItems) }
    }
}