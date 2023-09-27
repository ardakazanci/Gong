package com.ardakazanci.gong.core.navigation

sealed class NavigationFlow {
    class DetailFlow(val idValue: String,val name: String) : NavigationFlow()
}