package com.ardakazanci.gong.data.model


import com.ardakazanci.gong.data.Mapper
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import com.google.gson.annotations.SerializedName

class SatelliteListDataModel : ArrayList<SatelliteListDataModel.SatelliteListDataModelItem>(),Mapper<SatelliteListDomainModel> {
    data class SatelliteListDataModelItem(
        @SerializedName("active")
        val active: Boolean?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    ) : Mapper<SatelliteListDomainModel.SatelliteListDomainModelItem> {
        override fun cast(): SatelliteListDomainModel.SatelliteListDomainModelItem {
            return SatelliteListDomainModel.SatelliteListDomainModelItem(
                active ?: false,
                id ?: 0,
                name.orEmpty()
            )
        }

    }

    override fun cast(): SatelliteListDomainModel {
        return this.cast()
    }
}