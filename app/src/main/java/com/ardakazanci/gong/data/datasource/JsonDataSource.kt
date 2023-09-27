package com.ardakazanci.gong.data.datasource

import android.content.Context
import javax.inject.Inject

class JsonDataSource @Inject constructor(
    private val context: Context
) {
    fun getList(): String {
        return context.assets.open("satellite_list.json")
            .bufferedReader()
            .use { it.readText() }
    }

    fun getDetail(): String {
        return context.assets.open("satellite_detail.json").bufferedReader()
            .use { it.readText() }
    }

    fun getPositions() : String{
        return context.assets.open("positions.json").bufferedReader()
            .use { it.readText() }
    }
}