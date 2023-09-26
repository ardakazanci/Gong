package com.ardakazanci.gong.data.model


import com.google.gson.annotations.SerializedName

class SatelliteDetailDataModel : ArrayList<SatelliteDetailDataModel.SatelliteDetailDataModelItem>(){
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
    )
}