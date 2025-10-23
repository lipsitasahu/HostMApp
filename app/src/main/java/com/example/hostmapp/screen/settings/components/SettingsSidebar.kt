package com.example.hostmapp.screen.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsSidebar(selected: String, onSelect: (String) -> Unit) {
    val items = listOf("General", "Display", "Sound", "Navigation", "Vehicle")
    Column(
        modifier = Modifier
            .width(180.dp)
            .fillMaxHeight()
            .background(Color(0xFF1E1E1E))
            .padding(vertical = 16.dp)
    ) {
        items.forEach { item ->
            val isSelected = item == selected
            val bgColor = if (isSelected) Color(0xFF0097A7) else Color.Transparent
            val textColor = if (isSelected) Color.White else Color.LightGray

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(bgColor)
                    .clickable { onSelect(item) }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = item, color = textColor, fontSize = 16.sp)
            }
        }
    }
}