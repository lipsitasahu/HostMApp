package viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SettingsViewModel : ViewModel() {

    private val _dayNightMode = MutableStateFlow(true)
    val dayNightMode = _dayNightMode.asStateFlow()

    private val _highBeamAssist = MutableStateFlow(false)
    val highBeamAssist = _highBeamAssist.asStateFlow()

    private val _language = MutableStateFlow("English (US)")
    val language = _language.asStateFlow()

    private val _units = MutableStateFlow("km/h")
    val units = _units.asStateFlow()

    private val _autoUpdate = MutableStateFlow(false)
    val autoUpdate = _autoUpdate.asStateFlow()

    fun toggleDayNightMode() { _dayNightMode.value = !_dayNightMode.value }
    fun toggleHighBeamAssist() { _highBeamAssist.value = !_highBeamAssist.value }
    fun toggleAutoUpdate() { _autoUpdate.value = !_autoUpdate.value }
    fun updateLanguage(lang: String) { _language.value = lang }
    fun updateUnits(unit: String) { _units.value = unit }
}