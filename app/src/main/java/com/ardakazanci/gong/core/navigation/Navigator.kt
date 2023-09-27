package com.ardakazanci.gong.core.navigation

import androidx.navigation.NavController
import com.ardakazanci.gong.presentation.ListFragmentDirections

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when(navigationFlow){
        is NavigationFlow.DetailFlow -> navController.navigate(ListFragmentDirections.actionListFragmentToDetailFragment(navigationFlow.idValue,navigationFlow.name))
    }
}