package com.example.hostmapp.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hostmapp.screen.settings.components.SettingItem
import com.example.hostmapp.screen.settings.components.SettingsSidebar
import viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBackClick: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf("General") }

    val dayNight by viewModel.dayNightMode.collectAsState()
    val beamAssist by viewModel.highBeamAssist.collectAsState()
    val lang by viewModel.language.collectAsState()
    val units by viewModel.units.collectAsState()
    val autoUpdate by viewModel.autoUpdate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Row(Modifier.fillMaxSize()) {
            SettingsSidebar(selectedCategory, onSelect = { selectedCategory = it })

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "SETTINGS",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                when (selectedCategory) {
                    "General" -> {
                        SettingItem(
                            title = "Day/Night Mode",
                            isSwitch = true,
                            switchState = dayNight,
                            onSwitchChange = { viewModel.toggleDayNightMode() }
                        )
                        SettingItem(
                            title = "High-Beam Assist",
                            isSwitch = true,
                            switchState = beamAssist,
                            onSwitchChange = { viewModel.toggleHighBeamAssist() }
                        )
                        SettingItem(
                            title = "Language",
                            dropdownValue = lang,
                            dropdownOptions = listOf("English (US)", "English (UK)", "German", "French"),
                            onDropdownSelect = { viewModel.updateLanguage(it) }
                        )
                        SettingItem(
                            title = "Units (km/h / mph)",
                            dropdownValue = units,
                            dropdownOptions = listOf("km/h", "mph"),
                            onDropdownSelect = { viewModel.updateUnits(it) }
                        )
                        SettingItem(
                            title = "Automatic Updates",
                            isSwitch = true,
                            switchState = autoUpdate,
                            onSwitchChange = { viewModel.toggleAutoUpdate() }
                        )
                    }
                    else -> Text("Coming soon...", color = Color.Gray)
                }
            }
        }
    }
}