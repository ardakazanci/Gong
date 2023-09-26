package com.ardakazanci.gong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.ardakazanci.gong.core.navigation.NavigationFlow
import com.ardakazanci.gong.core.navigation.Navigator
import com.ardakazanci.gong.core.navigation.ToNavigate
import com.ardakazanci.gong.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToNavigate {

    lateinit var binding: ActivityMainBinding

    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        navigator = Navigator().apply {
            navController = navHostFragment.navController
        }
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }


}