package com.ardakazanci.gong.domain

import com.google.gson.annotations.SerializedName

class SatelliteListDomainModel : ArrayList<SatelliteListDomainModel.SatelliteListDomainModelItem>(){
    data class SatelliteListDomainModelItem(
        val active: Boolean = false,
        val id: Int = 0,
        val name: String = ""
    )
}