package com.example.hostmapp.screen.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RpmGauge(rpm: Float) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
        Canvas(modifier = Modifier.size(150.dp)) {
            val sweepAngle = (rpm / 8f) * 270f
            drawArc(
                color = Color(0xFF00BCD4),
                startAngle = 135f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 5f, cap = StrokeCap.Round)
            )
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("${rpm}", color = Color.White, fontSize = 18.sp)
            Text("RPM x 1000", color = Color.White, fontSize = 12.sp)
        }
    }
}