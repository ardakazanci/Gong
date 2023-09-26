package com.ardakazanci.gong.data.model


import com.google.gson.annotations.SerializedName

data class PositionsDataModel(
    @SerializedName("list")
    val list: List<ListCoreDataModel>?
) {
    data class ListCoreDataModel(
        @SerializedName("id")
        val id: String?,
        @SerializedName("positions")
        val positions: List<Position?>?
    ) {
        data class Position(
            @SerializedName("posX")
            val posX: Double?,
            @SerializedName("posY")
            val posY: Double?
        )
    }
}