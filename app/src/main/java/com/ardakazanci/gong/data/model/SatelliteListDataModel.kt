package com.ardakazanci.gong.data.model


import com.google.gson.annotations.SerializedName

class SatelliteListDataModel : ArrayList<SatelliteListDataModel.SatelliteListDataModelItem>(){
    data class SatelliteListDataModelItem(
        @SerializedName("active")
        val active: Boolean?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    )
}