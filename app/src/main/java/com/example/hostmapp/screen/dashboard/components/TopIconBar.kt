package com.example.hostmapp.screen.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryStd
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.DragIndicator
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock

@Composable
fun TopIconBar(batteryLevel: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(Icons.Filled.Camera, contentDescription = "Camera", tint = Color(0xFFFF9800))
        Icon(Icons.Filled.Lock, contentDescription = "Lock", tint = Color(0xFFFF9800))
        Icon(Icons.Filled.DragIndicator, contentDescription = "Indicator", tint = Color(0xFFFF9800))

        Box(contentAlignment = Alignment.Center) {
            Icon(Icons.Filled.BatteryStd, contentDescription = "Battery", tint = Color.Green)
            Text("${(batteryLevel * 100).toInt()}%", color = Color.White, fontSize = 10.sp)
        }
    }
}
