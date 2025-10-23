package com.example.hostmapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hostmapp.screen.dashboard.DashboardScreen
import com.example.hostmapp.screen.settings.SettingsScreen
import com.example.hostmapp.service.DashboardDataService
import viewmodel.DashboardViewModel
import viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    val dashboardViewModel = DashboardViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val settingsViewModel = SettingsViewModel()

        val intent = Intent(this, DashboardDataService::class.java)
        startService(intent)
        dashboardViewModel.bindService(this)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "dashboard") {
                composable("dashboard") {
                    DashboardScreen(
                        viewModel = dashboardViewModel,
                        onSettingsClick = { navController.navigate("settings") }
                    )
                }
                composable("settings") {
                    SettingsScreen(
                        viewModel = settingsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        dashboardViewModel.unbindService(this)
        super.onDestroy()
    }
}
