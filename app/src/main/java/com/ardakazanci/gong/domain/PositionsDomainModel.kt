package com.ardakazanci.gong.domain

data class PositionsDomainModel(
    val list: List<ListCoreDomainModel>
) {
    data class ListCoreDomainModel(
        val id: String = "",
        val positions: List<Position>
    ) {
        data class Position(
            val posX: Double = 0.0,
            val posY: Double = 0.0
        )
    }
}