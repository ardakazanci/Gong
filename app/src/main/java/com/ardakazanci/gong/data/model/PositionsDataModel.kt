package com.ardakazanci.gong.data.model


import com.ardakazanci.gong.data.Mapper
import com.ardakazanci.gong.domain.PositionsDomainModel
import com.google.gson.annotations.SerializedName

data class PositionsDataModel(
    @SerializedName("list")
    val list: List<ListCoreDataModel>?
) : Mapper<PositionsDomainModel> {
    data class ListCoreDataModel(
        @SerializedName("id")
        val id: String?,
        @SerializedName("positions")
        val positions: List<Position?>?
    ) : Mapper<PositionsDomainModel.ListCoreDomainModel> {
        data class Position(
            @SerializedName("posX")
            val posX: Double?,
            @SerializedName("posY")
            val posY: Double?
        ) : Mapper<PositionsDomainModel.ListCoreDomainModel.Position> {
            override fun cast(): PositionsDomainModel.ListCoreDomainModel.Position {
                return PositionsDomainModel.ListCoreDomainModel.Position(posX ?: 0.0, posY ?: 0.0)
            }
        }

        override fun cast(): PositionsDomainModel.ListCoreDomainModel {
            return PositionsDomainModel.ListCoreDomainModel(
                id.orEmpty(),
                positions?.mapNotNull { it?.cast() }.orEmpty()
            )
        }
    }

    override fun cast(): PositionsDomainModel {
        return PositionsDomainModel(list?.map { it.cast() }.orEmpty())
    }
}