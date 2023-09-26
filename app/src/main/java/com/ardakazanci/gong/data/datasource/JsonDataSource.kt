package com.ardakazanci.gong.data.datasource

import android.content.Context
import javax.inject.Inject

class JsonDataSource @Inject constructor(
    private val context: Context
) {
    suspend fun getList(): String {
        val listString = context.assets.open("satellite_list.json")
            .bufferedReader()
            .use { it.readText() }
        return listString
    }
}