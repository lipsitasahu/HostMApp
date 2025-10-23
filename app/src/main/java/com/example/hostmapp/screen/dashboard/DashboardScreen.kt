package com.example.hostmapp.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hostmapp.screen.dashboard.components.FuelBar
import com.example.hostmapp.screen.dashboard.components.RpmGauge
import com.example.hostmapp.screen.dashboard.components.Speedometer
import com.example.hostmapp.screen.dashboard.components.TopIconBar
import viewmodel.DashboardViewModel


@Composable
fun DashboardScreen(viewModel: DashboardViewModel,  onSettingsClick: () -> Unit) {
    val data by viewModel.dashboardData.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopIconBar(batteryLevel = data.batteryLevel)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Speedometer(speed = data.speed, gear = data.gear)
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RpmGauge(rpm = data.rpm)
                FuelBar(fuelLevel = data.fuelLevel)
            }
        }

        Button(
            onClick = onSettingsClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Go to Settings")
        }
    }
}
