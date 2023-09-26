package com.ardakazanci.gong.domain

class SatelliteDetailDomainModel : ArrayList<SatelliteDetailDomainModel.SatelliteDetailDomainModelItem>(){
    data class SatelliteDetailDomainModelItem(
        val costPerLaunch: Int = 0,
        val firstFlight: String = "",
        val height: Int = 0,
        val id: Int = 0,
        val mass: Int = 0
    )
}