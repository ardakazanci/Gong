package com.ardakazanci.gong.data.model


import com.ardakazanci.gong.data.Mapper
import com.ardakazanci.gong.domain.SatelliteListDomainModel
import com.google.gson.annotations.SerializedName

class SatelliteListDataModel : ArrayList<SatelliteListDataModel.SatelliteListDataModelItem>(), Mapper<SatelliteListDomainModel> {
    data class SatelliteListDataModelItem(
        @SerializedName("active")
        val active: Boolean?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    ) {
        fun mapToDomainModel(): SatelliteListDomainModel.SatelliteListDomainModelItem {
            return SatelliteListDomainModel.SatelliteListDomainModelItem(
                active = this.active ?: false,
                id = this.id ?: 0,
                name = this.name ?: ""
            )
        }
    }

    override fun cast() : SatelliteListDomainModel {
        val domainModelItems = this.map { it.mapToDomainModel() }
        return SatelliteListDomainModel().apply { addAll(domainModelItems) }
    }
}